package learning.sprigBoot.mongoDB.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import learning.sprigBoot.mongoDB.support.StringResponse;

@RestController
public class ProductController {
	@Autowired
	ProductService service;
//	@Autowired
//	ProductTypeService prodTypeService;

	@RequestMapping(method = RequestMethod.GET, value = "/product-types")
	public List<ProductType> getAllProductTypes() {
		return service.getAllProductTypes();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/products")
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/product-details")
	public List<ProductDetails> getAllProductDetails() {
		return service.getAllProductDetails();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
	public Product getProduct(@PathVariable String id) {
		return service.getProduct(id);
	}

//	@RequestMapping(method = RequestMethod.GET, value = "/products-by-type/{id}")
//	public List<Product> getProductByType(@PathVariable String id) {
//		return service.getProductByType(id);
//	}

	@RequestMapping(method = RequestMethod.GET, value = "/product-next-id", produces = "application/json")
	public StringResponse getNextProductId() {
		StringResponse response = new StringResponse();
		response.setResponse(service.getNextProductId());
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/products")
	public void insertProduct(@RequestBody Product prod) {
		System.out.println("Inserting Product");
		if (prod != null) {
			service.addProduct(prod);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/product-details")
	public void insertProduct(@RequestBody ProductDetails prodDet) {
		System.out.println("Inserting Product Details");
		if (prodDet != null) {
			service.addProductDetails(prodDet);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/products")
	public void updateProduct(@RequestBody Product prod) {
		if (prod != null) {
			service.updateProduct(prod);
		}
	}
	@RequestMapping(method = RequestMethod.GET, value = "/complete-products/{refDate}")
	public List<CompleteProduct> getAllCompleteProducts(@PathVariable String refDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return service.getAllCompleteProducts(sdf.parse(refDate));
		} catch (ParseException e) {
			e.printStackTrace(System.out);
		}
		return null;
	}
	@RequestMapping(method = RequestMethod.POST, value = "/complete-products/{refDate}")
	public void addCompleteProduct(@PathVariable String refDate, @RequestBody CompleteProduct completeProduct) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			service.addCompleteProduct(completeProduct, sdf.parse(refDate));
		} catch (ParseException e) {
			e.printStackTrace(System.out);
		}
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.PUT, value = "/complete-products/{refDate}")
	public void updateCompleteProduct(@PathVariable String refDate, @RequestBody CompleteProduct completeProduct) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			service.updateCompleteProduct(completeProduct, sdf.parse(refDate));
		} catch (ParseException e) {
			e.printStackTrace(System.out);
		}
	}
}
