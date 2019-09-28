package com.plash.configurator.repository;


import com.plash.configurator.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {

    @Query("Select b from Book as b where b.id=:id")
    public Book getBookById(@Param("id") Long id);

    @Query(value = "SELECT * from book where title=:title",nativeQuery = true)
    public Book getBookByTitle(@Param("title") String title);

    @Query("Update Book as b set b.version=:version where b.id=:id")
    public Book updateVersionById(@Param("version") String version, @Param("id") Long id);

    @Query("Delete from Book as b where b.id=:id")
    public Book deleteBookById(@Param("id") Long id);

}
