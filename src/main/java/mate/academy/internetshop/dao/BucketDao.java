package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;

@Dao
public interface BucketDao {

    Bucket create(Bucket bucket);

    Optional<Bucket> get(Long id);

    Bucket update(Bucket bucket);

    Bucket delete(Long id);

    Bucket addItem(Long bucketId, Long itemId);

    void removeItem(Long bucketId, Long itemId);

    void clear(Long bucketId);

    Optional<Bucket> getBucketByUserId(Long userId);
}
