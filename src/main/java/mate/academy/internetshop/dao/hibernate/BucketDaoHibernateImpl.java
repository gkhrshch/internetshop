package mate.academy.internetshop.dao.hibernate;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class BucketDaoHibernateImpl implements BucketDao {
    private static Logger logger = Logger.getLogger(BucketDaoHibernateImpl.class);

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
        return null;
    }

    @Override
    public Bucket delete(Long id) {
        return null;
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        return null;
    }

    @Override
    public void removeItem(Long bucketId, Long itemId) {

    }

    @Override
    public void clear(Long bucketId) {

    }

    @Override
    public Optional<Bucket> getBucketByUserId(Long userId) {
        return Optional.empty();
    }
}
