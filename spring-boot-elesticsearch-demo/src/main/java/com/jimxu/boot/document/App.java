package com.jimxu.boot.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 应用实体类
 * <p>
 * Created by xuzhijing on 18/07/2017.
 */
@Document(indexName = "boxstore", type = "app")
public class App implements Serializable {

    private static final long serialVersionUID = -1L;
    
    /**
     * 应用名称
     */
    @Id
    private String appKey;

    public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
     * 应用名称
     */
    private String name;
    
	/**
     * 上传者
     */
    private String uploader;
    /**
     * 描述
     */
    private String description;
    /**
     * 应用评分
     */
    private Integer score;
    /**
     * 应用类型
     */
    private Integer type;
    public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	/**
     * 应用下载次数
     */
    private Integer downloadNum;
    public Integer getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(Integer downloadNum) {
		this.downloadNum = downloadNum;
	}

	/**
     * 应用安全标识
     */
    private Integer sign;
    /**
     * 应用版本
     */
    private String version;
    /**
     * 开发者
     */
    private String author;
    /**
     * icon
     */
    private String icon;    
    
    /**
     * icon
     */
    private String iconUrl;    
    
    public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
     * 创建时间
     */
    private String datetime;
    /**
     * 应用包地址
     */
    private String url;

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

//	public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
