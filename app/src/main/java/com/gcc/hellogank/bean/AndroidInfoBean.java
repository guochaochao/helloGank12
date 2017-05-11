package com.gcc.hellogank.bean;

import java.util.List;

/**
 * Created by user on 2017/4/18.
 */

public class AndroidInfoBean {

    /**
     * error : false
     * results : [{"_id":"58f57e35421aa9544b774003","createdAt":"2017-04-18T10:47:17.437Z","desc":"Android 多边形绘制组件，做的很漂亮哦~ 用来做动态图表会很有帮助，同时可以学习他的实现。","images":["http://img.gank.io/5b728c1a-5685-4517-adc9-e1590e462097"],"publishedAt":"2017-04-18T11:34:29.203Z","source":"chrome","type":"Android","url":"https://github.com/stkent/PolygonDrawingUtil","used":true,"who":"代码家"},{"_id":"58f57e9f421aa9544b774005","createdAt":"2017-04-18T10:49:03.981Z","desc":"RxJava3 预览版 Demo","publishedAt":"2017-04-18T11:34:29.203Z","source":"chrome","type":"Android","url":"https://github.com/akarnokd/RxJava3-preview","used":true,"who":"带马甲"},{"_id":"58f1a04a421aa9544825f879","createdAt":"2017-04-15T12:23:38.405Z","desc":"CNode社区非官方客户端","images":["http://img.gank.io/8d023609-2037-4964-9571-bbeec2c9beeb"],"publishedAt":"2017-04-17T11:32:14.674Z","source":"web","type":"Android","url":"https://github.com/shellljx/CNode-android","used":true,"who":"li jinxiang"},{"_id":"58f42881421aa9544ed8895b","createdAt":"2017-04-17T10:29:21.119Z","desc":"Ramotion 出品：Android 页面展开效果","images":["http://img.gank.io/39d8b142-1ee1-477f-812c-ff1509994929"],"publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"Android","url":"https://github.com/Ramotion/expanding-collection-android","used":true,"who":"带马甲"},{"_id":"58f429a5421aa9544825f88a","createdAt":"2017-04-17T10:34:13.180Z","desc":"Android Tag Chip 效果，做的非常细腻，完全的 Material Design。","images":["http://img.gank.io/6e1eaf48-4277-46dc-b97b-f05a5261223b"],"publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"Android","url":"https://github.com/pchmn/MaterialChipsInput","used":true,"who":"代码家"},{"_id":"58ef1a55421aa9544ed88936","createdAt":"2017-04-13T14:27:33.889Z","desc":"java8 以下，使用Stream Api。","publishedAt":"2017-04-14T11:46:48.47Z","source":"web","type":"Android","url":"https://github.com/aNNiMON/Lightweight-Stream-API","used":true,"who":"瘦纸好过夏"},{"_id":"58ef1b1a421aa9544b773fd3","createdAt":"2017-04-13T14:30:50.522Z","desc":"Android Toast 原理分析","publishedAt":"2017-04-14T11:46:48.47Z","source":"web","type":"Android","url":"http://qlm.pw/2017/04/13/android-toast-%E5%8E%9F%E7%90%86%E5%88%86%E6%9E%90/","used":true,"who":"Linmin Qiu"},{"_id":"58efa248421aa954511ebe7e","createdAt":"2017-04-14T00:07:36.366Z","desc":"展示 Android 程序方法调用链的 gralde 插件，支持输出html文件和方法折叠","images":["http://img.gank.io/9be64918-c259-4f81-9ab5-341293a74fe4"],"publishedAt":"2017-04-14T11:46:48.47Z","source":"web","type":"Android","url":"https://github.com/CoXier/AppMethodTracking","used":true,"who":"CoXier"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 58f57e35421aa9544b774003
         * createdAt : 2017-04-18T10:47:17.437Z
         * desc : Android 多边形绘制组件，做的很漂亮哦~ 用来做动态图表会很有帮助，同时可以学习他的实现。
         * images : ["http://img.gank.io/5b728c1a-5685-4517-adc9-e1590e462097"]
         * publishedAt : 2017-04-18T11:34:29.203Z
         * source : chrome
         * type : Android
         * url : https://github.com/stkent/PolygonDrawingUtil
         * used : true
         * who : 代码家
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
