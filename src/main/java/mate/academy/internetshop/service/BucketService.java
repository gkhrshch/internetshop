package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Bucket;

public interface BucketService {

    Bucket addItem(Long BucketId, Long ItemId);
}
