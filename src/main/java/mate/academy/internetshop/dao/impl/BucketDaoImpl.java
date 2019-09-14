package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;

import java.util.NoSuchElementException;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        return null;
    }

    @Override
    public Bucket get(Long bucketId) {
        return Storage.buckets
                .stream()
                .filter(b -> b.getId().equals(bucketId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new
        ("Can't find bucket with id " + bucketId));
    }

    @Override
    public Bucket update(Bucket bucket) {
        return null;
    }

    @Override
    public Bucket delete(Bucket bucket) {

    }
}
