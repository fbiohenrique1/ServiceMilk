package com.pa2.milk.api.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.pa2.milk.api.model.AbstractModel;

@NoRepositoryBean
public interface GenericRepository<T extends AbstractModel<PK>, PK extends Serializable> extends JpaRepository<T, PK> {
    /**
     * Buscar todos ativos
     * 
     * @return objects
     */
    @Override
    @Query(value = "select * from #{#entityName} where ativo = true", nativeQuery = true)
    List<T> findAll();

    /**
     * Buscar um objeto ativo
     * 
     * @param arg0
     * @return object
     */
    @Override
    @Query(value = "select * from #{#entityName} where id = ?1 and ativo = true", nativeQuery = true)
    Optional<T> findById(PK arg0);

    /**
     * Atribui false no objeto a ser apagado logicamente pelo objeto
     * 
     * @param arg0
     */
    @Override
    default void delete(T arg0) {
        // TODO Auto-generated method stub
        deleteById(arg0.getId());
    }

    @Override
    @Transactional
    @Modifying
    @Query(value = "UPDATE #{#entityName} SET ativo=false where id = ?1", nativeQuery = true)
    void deleteById(PK arg0);

    @Override
    default void deleteAll(Iterable<? extends T> arg0) {
        arg0.forEach(entity -> {
            deleteById(entity.getId());
        });
    }
}
