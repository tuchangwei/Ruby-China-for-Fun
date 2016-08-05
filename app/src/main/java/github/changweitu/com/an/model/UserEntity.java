package github.changweitu.com.an.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by vale on 7/16/16.
 */
@Entity
public class UserEntity {

    @Unique
    @Id
    private long id;
    private String login;
    private String name;
    private String avatar_url;
    @ToMany(joinProperties = {
            @JoinProperty(name = "id", referencedName = "userId")
    })
    private List<TopicEntity> topics;

    public static void addOrUpdateUserInDB(UserEntity userEntity, User user) {
        userEntity.setId(user.getId());
        userEntity.setAvatar_url(user.getAvatar_url());
        userEntity.setLogin(user.getLogin());
        userEntity.setName(user.getName());
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
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1067351932)
    public synchronized void resetTopics() {
        topics = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 404517571)
    public List<TopicEntity> getTopics() {
        if (topics == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TopicEntityDao targetDao = daoSession.getTopicEntityDao();
            List<TopicEntity> topicsNew = targetDao._queryUserEntity_Topics(id);
            synchronized (this) {
                if(topics == null) {
                    topics = topicsNew;
                }
            }
        }
        return topics;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 287999134)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserEntityDao() : null;
    }
    /** Used for active entity operations. */
    @Generated(hash = 1814575071)
    private transient UserEntityDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public String getAvatar_url() {
        return this.avatar_url;
    }
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLogin() {
        return this.login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 1028278424)
    public UserEntity(long id, String login, String name, String avatar_url) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.avatar_url = avatar_url;
    }
    @Generated(hash = 1433178141)
    public UserEntity() {
    }
}
