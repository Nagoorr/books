package com.plash.configurator.repository;

import com.plash.configurator.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LanguageRepository extends JpaRepository<Language,Long>
{
    @Query("Select l from Language where l.lid=:lid and l.lName=:lname")
    public Language getLanguageByIdAndLang(@Param("lid") Long id,@Param("lname") String lName);

    @Query("Select l from Language where l.lid=:id")
    public Language getLanguageById(@Param("id") Long id);
}
