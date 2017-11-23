package com.jimxu.boot.service;

import java.util.Iterator;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.jimxu.boot.document.App;
import com.jimxu.boot.repository.AppRepository;

/**
 * 应用 ES 业务逻辑实现类
 * <p>
 * Created by xuzhijing on 13/07/2017.
 */
@Service
public class AppESServiceImpl implements AppESService {

    // 分页参数 -> TODO 代码可迁移到具体项目的公共 common 模块
    private final Integer pageNumber = 0;
    private final Integer pageSize = 10;
    Pageable pageable = new PageRequest(pageNumber, pageSize);
    
    /* 搜索模式 */
    String SCORE_MODE_SUM = "sum"; // 权重分求和模式
    Float  MIN_SCORE = 10.0F;      // 由于无相关性的分值默认为 1 ，设置权重分最小值为 10

    // ES 操作类
    @Autowired
    AppRepository appRepository;

    public String saveApp(App App) {
        App AppResult = appRepository.save(App);
        return AppResult.getName();
    }

    @Override
	public void deleteApp(String id) {
    	appRepository.delete(id);
	}

	public List<App> findByDescriptionAndScore(String description, Integer score) {
        return appRepository.findByDescriptionAndScore(description, score);
    }

    public List<App> findByDescriptionOrScore(String description, Integer score) {
        return appRepository.findByDescriptionOrScore(description, score);
    }

    public List<App> findByDescriptionOrName(String description, String name){
    	return appRepository.findByDescriptionOrName(description, name);
    }
    
    public List<App> findByDescription(String description) {
        return appRepository.findByDescription(description, pageable).getContent();
    }

    @Override
	public List<App> findByName(String name) {	
		return appRepository.findByName(name, pageable).getContent();
	}

	public List<App> findByDescriptionNot(String description) {
        return appRepository.findByDescriptionNot(description, pageable).getContent();
    }

    public List<App> findByDescriptionLike(String description) {
        return appRepository.findByDescriptionLike(description, pageable).getContent();
    }
    
    public List<App> findByNameLike(String name) {
        return appRepository.findByNameLike(name, pageable).getContent();
    }
    
    @Override
	public List<App> findByUploader(String uploader) {
        return appRepository.findByUploader(uploader, pageable).getContent();
	}

	@Override
	public List<App> findByAuthorLike(String author) {
	
		return appRepository.findByAuthorLike(author, pageable).getContent();
	}

	@Override
	public List<App> findByUploaderLike(String uploader) {	
		return appRepository.findByUploaderLike(uploader, pageable).getContent();
	}

	public List<App> findAll(Integer pageNum,Integer pageSize) {
    	pageable= new PageRequest(pageNum, pageSize);
        return appRepository.findAll(pageable).getContent();
    }
    
    public Iterator<App> findAll() {
        return appRepository.findAll().iterator();
    }
    
    @Override
    public List<App> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {

        // 校验分页参数
        if (pageSize == null || pageSize <= 0) {
            pageSize = this.pageSize;
        }

        if (pageNumber == null || pageNumber < pageNumber) {
            pageNumber = this.pageNumber;
        }

        System.out.println("\n searchCity: searchContent [" + searchContent + "] \n ");

        // 构建搜索查询
        SearchQuery searchQuery = getAppSearchQuery(pageNumber,pageSize,searchContent);

        System.out.println("\n searchCity: searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());

        Page<App> cityPage = appRepository.search(searchQuery);
        return cityPage.getContent();
    }
    
    /**
     * 根据搜索词构造搜索查询语句
     *
     * 代码流程：
     *      - 权重分查询
     *      - 短语匹配
     *      - 设置权重分最小值
     *      - 设置分页参数
     *
     * @param pageNumber 当前页码
     * @param pageSize 每页大小
     * @param searchContent 搜索内容
     * @return
     */
    private SearchQuery getAppSearchQuery(Integer pageNumber, Integer pageSize,String searchContent) {
        // 短语匹配到的搜索词，求和模式累加权重分
        // 权重分查询 https://www.elastic.co/guide/cn/elasticsearch/guide/current/function-score-query.html
        //   - 短语匹配 https://www.elastic.co/guide/cn/elasticsearch/guide/current/phrase-matching.html
        //   - 字段对应权重分设置，可以优化成 enum
        //   - 由于无相关性的分值默认为 1 ，设置权重分最小值为 10
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.matchPhraseQuery("name", searchContent),ScoreFunctionBuilders.weightFactorFunction(1000))
                .add(QueryBuilders.matchPhraseQuery("description", searchContent),ScoreFunctionBuilders.weightFactorFunction(500))
                .scoreMode(SCORE_MODE_SUM).setMinScore(MIN_SCORE);
        // 分页参数
        this.pageable = new PageRequest(pageNumber, pageSize);
        return new NativeSearchQueryBuilder().withPageable(pageable).withQuery(functionScoreQueryBuilder).build();
    }

}
