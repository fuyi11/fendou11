package com.fuxia.w.bean;

import java.util.List;

/**
 * Created by fuyi on 2016/12/26.
 */

public class HomeCommentInfo {
    private String message;

    private String status;

    private List<CommentInfo> data ;

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setData(List<CommentInfo> data){
        this.data = data;
    }
    public List<CommentInfo> getData(){
        return this.data;
    }



    public class CommentInfo {
        private String uid;

        private String user_name;

        private String content;

        private String add_time;

        private String project_id;

        private String id;

        private String dz_count;

        private String head_url;

        private List<Chd> chd;

        private String chd_count;

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_name() {
            return this.user_name;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAdd_time() {
            return this.add_time;
        }

        public void setProject_id(String project_id) {
            this.project_id = project_id;
        }

        public String getProject_id() {
            return this.project_id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public void setDz_count(String dz_count) {
            this.dz_count = dz_count;
        }

        public String getDz_count() {
            return this.dz_count;
        }

        public void setChd(List<Chd> chd) {
            this.chd = chd;
        }

        public List<Chd> getChd() {
            return this.chd;
        }

        public void setChd_count(String chd_count) {
            this.chd_count = chd_count;
        }

        public String getChd_count() {
            return this.chd_count;
        }

        public String getHead_url() {
            return head_url;
        }

        public void setHead_url(String head_url) {
            this.head_url = head_url;
        }
    }

    public class Chd {
        private String uid;

        private String user_name;

        private String content;

        private String add_time;

        private String project_id;

        private String id;

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_name() {
            return this.user_name;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAdd_time() {
            return this.add_time;
        }

        public void setProject_id(String project_id) {
            this.project_id = project_id;
        }

        public String getProject_id() {
            return this.project_id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }
    }
}
