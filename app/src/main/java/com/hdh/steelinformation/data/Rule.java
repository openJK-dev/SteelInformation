package com.hdh.steelinformation.data;

public class Rule {
    /**
     * 链接
     */
    private String url;
    /**
     * 参数集合
     */
    private String[] params;
    /**
     * 参数对应的值
     */
    private String[] values;

    /**
     * 对返回的HTML，第一次过滤所用的标签，请先设置type
     */
    private String resultTagName;

    /**
     * CLASS / ID / SELECTION
     * 设置resultTagName的类型，默认为ID
     */
    private int type = ID;

    /**
     * GET / POST
     * 请求的类型，默认GET
     */
    private int requestMoethod = GET;

    /**
     * 连接类型（铁，铜，铝，不锈钢）
     */
    private String urlType;

    /**
     * 区域类型
     */
    private String areaType;
    /**
     * 链接名称
     */
    private String urlName;

    public final static int GET = 0;
    public final static int POST = 1;


    public final static int CLASS = 0;
    public final static int ID = 1;
    public final static int SELECTION = 2;

    public Rule() {
    }


    public Rule(String url, String[] params, String[] values,
                String resultTagName, int type, int requestMoethod) {
        super();
        this.url = url;
        this.params = params;
        this.values = values;
        this.resultTagName = resultTagName;
        this.type = type;
        this.requestMoethod = requestMoethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getResultTagName() {
        return resultTagName;
    }

    public void setResultTagName(String resultTagName) {
        this.resultTagName = resultTagName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRequestMoethod() {
        return requestMoethod;
    }

    public void setRequestMoethod(int requestMoethod) {
        this.requestMoethod = requestMoethod;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }
}

/**
 * 枚举，标记获取值的类型
 */
enum RESULTTYPE {
    LINKVALUE, STRINGVALUE
}
