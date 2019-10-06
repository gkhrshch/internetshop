package mate.academy.internetshop.service.impl;

import java.util.List;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import org.apache.log4j.Logger;

@Service
public class BucketServiceImpl implements BucketService {
    private static Logger logger = Logger.getLogger(BucketServiceImpl.class);

    @Inject
    private static BucketDao bucketDao;
    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket create(Bucket bucket) {
        bucketDao.create(bucket);
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        Bucket bucket = bucketDao.get(id).get();
        return bucket;
    }

    @Override
    public Bucket update(Long id) {
        Bucket bucket = bucketDao.update(get(id));
        return bucket;
    }

    @Override
    public Bucket delete(Long id) {
        return bucketDao.delete(id);
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId).get();
        bucketDao.addItem(bucketId, itemId);
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket removeItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId).get();
        bucketDao.removeItem(bucketId, itemId);
        return bucketDao.update(bucket);
    }

    public Bucket clear(Long bucketId) {
        bucketDao.clear(bucketId);
        return bucketDao.update(bucketDao.get(bucketId).get());
    }

    public List<Item> getAllItems(Bucket bucket) {
        return bucketDao.get(bucket.getId()).get().getItems();
    }

    @Override
    public Bucket getBucketByUserId(Long userId) {
        return bucketDao.getBucketByUserId(userId).get();
    }
}
