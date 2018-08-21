package com.gtechoogle.beautyrank.bean;

import java.util.List;

public class Gallery {
    private List<GalleryBean> gallery;

    public List<GalleryBean> getGallery() {
        return gallery;
    }

    public void setGallery(List<GalleryBean> gallery) {
        this.gallery = gallery;
    }

    public static class GalleryBean {
        /**
         * name : 杨幂
         * url : [{"link":"https://b-ssl.duitang.com/uploads/blog/201410/03/20141003195253_nhxEk.thumb.1400_0.jpeg"},{"link":"https://b-ssl.duitang.com/uploads/blog/201410/03/20141003195253_nhxEk.thumb.1400_0.jpeg"}]
         */

        private String name;
        private List<UrlBean> url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<UrlBean> getUrl() {
            return url;
        }

        public void setUrl(List<UrlBean> url) {
            this.url = url;
        }

        public static class UrlBean {
            /**
             * link : https://b-ssl.duitang.com/uploads/blog/201410/03/20141003195253_nhxEk.thumb.1400_0.jpeg
             */

            private String link;

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }
    }
}
