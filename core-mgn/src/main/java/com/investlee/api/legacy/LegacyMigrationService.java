package com.investlee.api.legacy;

import com.investlee.domain.advance.AdvanceBaseEntity;
import com.investlee.domain.legacy.LegacyBaseEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class LegacyMigrationService<
        Legacy extends LegacyBaseEntity,
        Advance extends AdvanceBaseEntity
        > implements MigrationService {

    protected CrudRepository<Legacy, Long> legacyRepository;
    protected CrudRepository<Advance, Long> advanceRepository;
    protected LegacyConverter<Legacy, Advance> converter;

    public LegacyMigrationService(
            CrudRepository<Legacy, Long> legacyRepository,
            CrudRepository<Advance, Long> advanceRepository,
            LegacyConverter<Legacy, Advance> converter) {
        this.legacyRepository = legacyRepository;
        this.advanceRepository = advanceRepository;
        this.converter = converter;
    }

    @Override
    public boolean migrate(Long id) {
        try {
            migrate(findLegacy(id));
            return true;
        } catch (RuntimeException e) {
            log.error("migration error", e);
            return false;
        }
    }

    private Legacy findLegacy(Long id) {
        return legacyRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    protected void migrate(Legacy legacy) {
        if (legacy.isDeleted()) {
            deleteAdvance(legacy.getId());
        } else {
            saveAdvance(convert(legacy));
        }
    }

    private void deleteAdvance(Long id) {
        advanceRepository.findById(id)
                .ifPresent(Advance -> advanceRepository.delete(Advance));
    }

    private Advance convert(Legacy legacy) {
        return converter.convert(legacy);
    }

    private void saveAdvance(Advance convert) {
        advanceRepository.save(convert);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean migrate(List<Legacy> legacies) {
        try {
            saveAdvances(convert(legacies));
            return true;
        } catch (RuntimeException e) {
            log.error("list migration error", e);
            return false;
        }
    }

    private List<Advance> convert(List<Legacy> legacies) {
        return legacies.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private void saveAdvances(List<Advance> convert) {
        advanceRepository.saveAll(convert);
    }
}
