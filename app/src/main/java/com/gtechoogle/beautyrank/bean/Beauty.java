package com.gtechoogle.beautyrank.bean;

import java.util.List;

/**
 * Created by edy on 2018/8/21.
 */

public class Beauty {
    private List<DatasheetBean> datasheet;

    public List<DatasheetBean> getDatasheet() {
        return datasheet;
    }

    public void setDatasheet(List<DatasheetBean> datasheet) {
        this.datasheet = datasheet;
    }

    public static class DatasheetBean {
        /**
         * date : 2018-08-21
         * info : {"name":"lp","birth":"2018-09-01","age":28,"nationality":"China","bwh":"10,10,10","description":""}
         * gallery : [{"url":"https://img.onvshen.com:85/gallery/23739/23166/s/0.jpg"},{"url":"https://img.onvshen.com:85/gallery/23739/23166/s/001.jpg"}]
         */

        private String date;
        private InfoBean info;
        private List<GalleryBean> gallery;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<GalleryBean> getGallery() {
            return gallery;
        }

        public void setGallery(List<GalleryBean> gallery) {
            this.gallery = gallery;
        }

        public static class InfoBean {
            /**
             * name : lp
             * birth : 2018-09-01
             * age : 28
             * nationality : China
             * bwh : 10,10,10
             * description :
             */

            private String name;
            private String birth;
            private int age;
            private String nationality;
            private String bwh;
            private String description;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBirth() {
                return birth;
            }

            public void setBirth(String birth) {
                this.birth = birth;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getNationality() {
                return nationality;
            }

            public void setNationality(String nationality) {
                this.nationality = nationality;
            }

            public String getBwh() {
                return bwh;
            }

            public void setBwh(String bwh) {
                this.bwh = bwh;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class GalleryBean {
            /**
             * url : https://img.onvshen.com:85/gallery/23739/23166/s/0.jpg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
