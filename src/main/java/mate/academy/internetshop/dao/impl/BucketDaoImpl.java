package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        return Storage.buckets
                .stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                "Can't find bucket with id " + id));
    }

    @Override
    public Bucket update(Bucket toUpdate) {
        Bucket bucket = get(toUpdate.getId());
        bucket.setItems(toUpdate.getItems());
        return bucket;
    }

    @Override
    public Bucket delete(Long id) {
        Bucket bucket = get(id);
        Storage.buckets.remove(bucket);
        return bucket;
    }
}
