package github.changweitu.com.an.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Date;
import java.util.HashMap;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.query.Query;

/**
 * Created by vale on 7/25/16.
 */
@Entity
public class TopicEntity {

   @Unique
   @Id
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
   private long userId;
   private int page;
   
   @ToOne(joinProperty = "userId")
   private UserEntity user;

   public static void addOrUpdateTopicInDB(TopicEntity topicEntity, Topic topic, int page) {
      topicEntity.setId(topic.getId());
      topicEntity.setTitle(topic.getTitle());
      topicEntity.setCreated_at(topic.getCreated_at());
      topicEntity.setUpdated_at(topic.getUpdated_at());
      topicEntity.setReplied_at(topic.getReplied_at());
      topicEntity.setReplies_count(topic.getReplies_count());
      topicEntity.setNode_name(topic.getNode_name());
      topicEntity.setNode_id(topic.getNode_id());
      topicEntity.setLast_reply_user_id(topic.getLast_reply_user_id());
      topicEntity.setLast_reply_user_login(topic.getLast_reply_user_login());
      topicEntity.setDeleted(topic.isDeleted());
      topicEntity.setExcellent(topic.isExcellent());
      topicEntity.setLikes_count(topic.getLikes_count());
      topicEntity.setUserId(topic.getUser().getId());
      topicEntity.setPage(page);

   }
   /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
    * Entity must attached to an entity context.
    */
   @Generated(hash = 1942392019)
   public void refresh() {
      if (myDao == null) {
         throw new DaoException("Entity is detached from DAO context");
      }
      myDao.refresh(this);
   }
   /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
    * Entity must attached to an entity context.
    */
   @Generated(hash = 713229351)
   public void update() {
      if (myDao == null) {
         throw new DaoException("Entity is detached from DAO context");
      }
      myDao.update(this);
   }
   /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
    * Entity must attached to an entity context.
    */
   @Generated(hash = 128553479)
   public void delete() {
      if (myDao == null) {
         throw new DaoException("Entity is detached from DAO context");
      }
      myDao.delete(this);
   }
   /** called by internal mechanisms, do not call yourself. */
   @Generated(hash = 1333627594)
   public void setUser(@NotNull UserEntity user) {
      if (user == null) {
         throw new DaoException(
               "To-one property 'userId' has not-null constraint; cannot set to-one to null");
      }
      synchronized (this) {
         this.user = user;
         userId = user.getId();
         user__resolvedKey = userId;
      }
   }
   /** To-one relationship, resolved on first access. */
   @Generated(hash = 1383836450)
   public UserEntity getUser() {
      long __key = this.userId;
      if (user__resolvedKey == null || !user__resolvedKey.equals(__key)) {
         final DaoSession daoSession = this.daoSession;
         if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
         }
         UserEntityDao targetDao = daoSession.getUserEntityDao();
         UserEntity userNew = targetDao.load(__key);
         synchronized (this) {
            user = userNew;
            user__resolvedKey = __key;
         }
      }
      return user;
   }
   @Generated(hash = 251390918)
   private transient Long user__resolvedKey;
   /** called by internal mechanisms, do not call yourself. */
   @Generated(hash = 2130265760)
   public void __setDaoSession(DaoSession daoSession) {
      this.daoSession = daoSession;
      myDao = daoSession != null ? daoSession.getTopicEntityDao() : null;
   }
   /** Used for active entity operations. */
   @Generated(hash = 1377002717)
   private transient TopicEntityDao myDao;
   /** Used to resolve relations */
   @Generated(hash = 2040040024)
   private transient DaoSession daoSession;
   public long getUserId() {
      return this.userId;
   }
   public void setUserId(long userId) {
      this.userId = userId;
   }
   public int getLikes_count() {
      return this.likes_count;
   }
   public void setLikes_count(int likes_count) {
      this.likes_count = likes_count;
   }
   public boolean getExcellent() {
      return this.excellent;
   }
   public void setExcellent(boolean excellent) {
      this.excellent = excellent;
   }
   public boolean getDeleted() {
      return this.deleted;
   }
   public void setDeleted(boolean deleted) {
      this.deleted = deleted;
   }
   public String getLast_reply_user_login() {
      return this.last_reply_user_login;
   }
   public void setLast_reply_user_login(String last_reply_user_login) {
      this.last_reply_user_login = last_reply_user_login;
   }
   public int getLast_reply_user_id() {
      return this.last_reply_user_id;
   }
   public void setLast_reply_user_id(int last_reply_user_id) {
      this.last_reply_user_id = last_reply_user_id;
   }
   public int getNode_id() {
      return this.node_id;
   }
   public void setNode_id(int node_id) {
      this.node_id = node_id;
   }
   public String getNode_name() {
      return this.node_name;
   }
   public void setNode_name(String node_name) {
      this.node_name = node_name;
   }
   public int getReplies_count() {
      return this.replies_count;
   }
   public void setReplies_count(int replies_count) {
      this.replies_count = replies_count;
   }
   public Date getReplied_at() {
      return this.replied_at;
   }
   public void setReplied_at(Date replied_at) {
      this.replied_at = replied_at;
   }
   public Date getUpdated_at() {
      return this.updated_at;
   }
   public void setUpdated_at(Date updated_at) {
      this.updated_at = updated_at;
   }
   public Date getCreated_at() {
      return this.created_at;
   }
   public void setCreated_at(Date created_at) {
      this.created_at = created_at;
   }
   public String getTitle() {
      return this.title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public long getId() {
      return this.id;
   }
   public void setId(long id) {
      this.id = id;
   }
   public int getPage() {
      return this.page;
   }
   public void setPage(int page) {
      this.page = page;
   }
   @Generated(hash = 21318122)
   public TopicEntity(long id, String title, Date created_at, Date updated_at,
         Date replied_at, int replies_count, String node_name, int node_id,
         int last_reply_user_id, String last_reply_user_login, boolean deleted,
         boolean excellent, int likes_count, long userId, int page) {
      this.id = id;
      this.title = title;
      this.created_at = created_at;
      this.updated_at = updated_at;
      this.replied_at = replied_at;
      this.replies_count = replies_count;
      this.node_name = node_name;
      this.node_id = node_id;
      this.last_reply_user_id = last_reply_user_id;
      this.last_reply_user_login = last_reply_user_login;
      this.deleted = deleted;
      this.excellent = excellent;
      this.likes_count = likes_count;
      this.userId = userId;
      this.page = page;
   }
   @Generated(hash = 916148810)
   public TopicEntity() {
   }


}
