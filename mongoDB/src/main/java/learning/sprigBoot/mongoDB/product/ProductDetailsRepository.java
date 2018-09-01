package learning.sprigBoot.mongoDB.product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductDetailsRepository extends MongoRepository<ProductDetails, TimeProduct> {
	
}
