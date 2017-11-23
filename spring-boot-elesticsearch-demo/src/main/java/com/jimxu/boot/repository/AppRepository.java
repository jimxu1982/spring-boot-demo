package com.jimxu.boot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jimxu.boot.document.App;

/**
 * ES 操作类
 * <p>
 * Created by xuzhijing on 17/07/2017.
 */
public interface AppRepository extends ElasticsearchRepository<App, String> {
    /**
     * AND 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    List<App> findByDescriptionAndScore(String description, Integer score);

    /**
     * OR 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    List<App> findByDescriptionOrScore(String description, Integer score);
    
    /**
     * OR 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    List<App> findByDescriptionOrName(String description, String name);

    /**
     * 查询城市描述
     *
     * 等同于下面代码
     * @Query("{\"bool\" : {\"must\" : {\"term\" : {\"description\" : \"?0\"}}}}")
     * Page<App> findByDescription(String description, Pageable pageable);
     *
     * @param description
     * @param page
     * @return
     */
    Page<App> findByUploader(String uploader, Pageable page);
    
    /**
     * 查询城市描述
     *
     * 等同于下面代码
     * @Query("{\"bool\" : {\"must\" : {\"term\" : {\"description\" : \"?0\"}}}}")
     * Page<App> findByDescription(String description, Pageable pageable);
     *
     * @param description
     * @param page
     * @return
     */
    Page<App> findByName(String name, Pageable page);
    
    /**
     * 查询城市描述
     *
     * 等同于下面代码
     * @Query("{\"bool\" : {\"must\" : {\"term\" : {\"description\" : \"?0\"}}}}")
     * Page<App> findByDescription(String description, Pageable pageable);
     *
     * @param description
     * @param page
     * @return
     */
    Page<App> findByDescription(String description, Pageable page);

    /**
     * NOT 语句查询
     *
     * @param description
     * @param page
     * @return
     */
    Page<App> findByDescriptionNot(String description, Pageable page);

    /**
     * LIKE 语句查询
     *
     * @param description
     * @param page
     * @return
     */
    Page<App> findByDescriptionLike(String description, Pageable page);
    
    /**
     * LIKE 语句查询
     *
     * @param description
     * @param page
     * @return
     */
    Page<App> findByNameLike(String name, Pageable page);
    
    /**
     * LIKE 语句查询
     *
     * @param author
     * @param page
     * @return
     */
    Page<App> findByAuthorLike(String author, Pageable page);
    /**
     * LIKE 语句查询
     *
     * @param author
     * @param page
     * @return
     */
    Page<App> findByUploaderLike(String uploader, Pageable page);

}
