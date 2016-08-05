package github.changweitu.com.an.model;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by vale on 7/25/16.
 */

public class Topic {
   public final static String POST_TOP = "精华帖";
   public final static String POST_NO_REPLY = "无人问津";
   public final static String POST_LAST_REPLY = "最后回复";
   public final static String POST_NEW = "最新创建";

   public final static HashMap<String, String> types;
   static {
      types = new HashMap<>();
      types.put(POST_TOP, "excellent");
      types.put(POST_NO_REPLY, "no_reply");
      types.put(POST_LAST_REPLY, "last_actived");
      types.put(POST_NEW, "recent");
   }

   private long id;
   private String title;
   private Date created_at;
   private Date updated_at;
   private Date replied_at;
   private int replies_count;
   private String node_name;
   private int node_id;
   private int last_reply_user_id;
   private String last_reply_user_login;
   private boolean deleted;
   private boolean excellent;
   private int likes_count;
   private User user;

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public Date getCreated_at() {
      return created_at;
   }

   public void setCreated_at(Date created_at) {
      this.created_at = created_at;
   }

   public Date getUpdated_at() {
      return updated_at;
   }

   public void setUpdated_at(Date updated_at) {
      this.updated_at = updated_at;
   }

   public Date getReplied_at() {
      return replied_at;
   }

   public void setReplied_at(Date replied_at) {
      this.replied_at = replied_at;
   }

   public int getReplies_count() {
      return replies_count;
   }

   public void setReplies_count(int replies_count) {
      this.replies_count = replies_count;
   }

   public String getNode_name() {
      return node_name;
   }

   public void setNode_name(String node_name) {
      this.node_name = node_name;
   }

   public int getNode_id() {
      return node_id;
   }

   public void setNode_id(int node_id) {
      this.node_id = node_id;
   }

   public int getLast_reply_user_id() {
      return last_reply_user_id;
   }

   public void setLast_reply_user_id(int last_reply_user_id) {
      this.last_reply_user_id = last_reply_user_id;
   }

   public String getLast_reply_user_login() {
      return last_reply_user_login;
   }

   public void setLast_reply_user_login(String last_reply_user_login) {
      this.last_reply_user_login = last_reply_user_login;
   }

   public boolean isDeleted() {
      return deleted;
   }

   public void setDeleted(boolean deleted) {
      this.deleted = deleted;
   }

   public boolean isExcellent() {
      return excellent;
   }

   public void setExcellent(boolean excellent) {
      this.excellent = excellent;
   }

   public int getLikes_count() {
      return likes_count;
   }

   public void setLikes_count(int likes_count) {
      this.likes_count = likes_count;
   }
}
