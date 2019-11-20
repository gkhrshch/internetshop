package mate.academy.internetshop.dao.hibernate;

import java.util.List;
import java.util.Optional;
import javax.persistence.Query;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class BucketDaoHibernateImpl implements BucketDao {
    private static Logger logger = Logger.getLogger(BucketDaoHibernateImpl.class);

    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket create(Bucket bucket) {
        Long bucketId = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            bucketId = (Long) session.save(bucket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        bucket.setId(bucketId);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Bucket bucket = session.get(Bucket.class, id);
            return Optional.of(bucket);
        }
    }

    @Override
    public Bucket update(Bucket bucket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(bucket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return bucket;
    }

    @Override
    public Bucket delete(Long id) {
        Bucket bucket = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            bucket = get(id).get();
            session.delete(bucket);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't delete item from bucket ", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return bucket;
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Bucket bucket = get(bucketId).get();
            List<Item> list = bucket.getItems();
            Item item = itemDao.get(itemId).get();
            list.add(item);
            update(bucket);
            return bucket;
        } catch (Exception e) {
            logger.error("Can't add item to bucket ", e);
            return null;
        }
    }

    @Override
    public void removeItem(Long bucketId, Long itemId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Bucket bucket = get(bucketId).get();
            List<Item> list = bucket.getItems();
            Item toDelete = itemDao.get(itemId).get();
            for (Item item : list) {
                if (item.getId().equals(itemId)) {
                    list.remove(item);
                    break;
                }
            }
            update(bucket);
        } catch (Exception e) {
            logger.error("Can't add item to bucket ", e);
        }
    }

    @Override
    public void clear(Long bucketId) {
        try {
            get(bucketId).get().getItems().clear();
        } catch (Exception e) {
            logger.error("Can't clear bucket ", e);
        }
    }

    @Override
    public Optional<Bucket> getBucketByUserId(Long userId) {
        Bucket bucket = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Bucket WHERE user_id=:userId");
            query.setParameter("userId", userId);
            bucket = (Bucket) ((org.hibernate.query.Query) query).uniqueResult();
        }
        return Optional.of(bucket);
    }
}
