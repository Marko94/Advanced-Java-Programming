angular.module('njpproject', [])
	.controller('loginCtrl', ['$scope','ServiceUser', function($scope, ServiceUser) {
		$scope.user = {};
		$scope.validateLogin; 
		$scope.showLoginForm = false;
		$scope.showLogoutBtn = false;
		$scope.login = function(){
			var user;
			user = angular.copy($scope.user);
			ServiceUser.login(user).then(function(response){
				var loginResponse = response.data;
				console.log(loginResponse);
				$scope.validateLogin = loginResponse;
			});
		};
	}])
	.controller('registerCtrl', ['$scope','ServiceUser', function($scope, ServiceUser) {
		$scope.user = {};
		$scope.validate; 
		$scope.showForm = false;
		$scope.showBtn = false;
		$scope.register = function(){
			var user;
			user = angular.copy($scope.user);
			ServiceUser.register(user).then(function(response){
				var res = response.data;
				console.log(res);
				$scope.validate = res;
			});
		};
	}])
	.factory('ServiceUser', ['$http', function($http) {
		var service = {};
		
		service.login = function(user) {
			console.log(user);
			return $http.post('http://localhost:8080/Client/rest/rent/login', {"username":user.username, "password":user.password });
		}
		
		service.register = function(user) {
			return $http.post('http://localhost:8080/Client/rest/rent/register', {"username":user.username, "password":user.password });
		}
		
		return service;
	}])
	.controller('addCarCtrl', ['$scope','ServiceCar', function($scope, ServiceCar) {
		$scope.car = {};
		$scope.validate; 
		$scope.showForm = false;
		$scope.showBtn = false;
		$scope.addCar = function(){
			var car;
			car = angular.copy($scope.car);
			ServiceCar.add(car).then(function(response){
				var res = response.data;
				console.log(res);
				$scope.validate = res;
			});
		};
	}])
	.controller('removeCarCtrl', ['$scope','ServiceCar', function($scope, ServiceCar) {
		$scope.car = {};
		$scope.validate; 
		$scope.showForm = false;
		$scope.showBtn = false;
		$scope.removeCar = function(){
			var car;
			car = angular.copy($scope.car);
			ServiceCar.remove(car.plate).then(function(response){
				var res = response.data;
				console.log(res);
				$scope.validate = res;
			});
		};
	}])
	.controller('listCarCtrl', ['$scope','ServiceCar', function($scope, ServiceCar) {
		$scope.showForm = false;
		$scope.showBtn = false;
		$scope.listCar = function(){
			ServiceCar.list().then(function(response){
				var res = response.data;
				console.log(res);
				$scope.validate = res;
			});
		};
	}])
	.controller('rentCarCtrl', ['$scope','ServiceCar', function($scope, ServiceCar) {
		$scope.plate;
		$scope.jmbg;
		$scope.showForm = false;
		$scope.showBtn = false;
		$scope.rentCar = function(){
			var plate, jmbg;
			plate = angular.copy($scope.plate);
			jmbg = angular.copy($scope.jmbg);
			ServiceCar.rent(plate, jmbg).then(function(response){
				var res = response.data;
				console.log(res);
				$scope.validate = res;
			});
		};
	}])
	.controller('returnCarCtrl', ['$scope','ServiceCar', function($scope, ServiceCar) {
		$scope.plate;
		$scope.jmbg;
		$scope.showForm = false;
		$scope.showBtn = false;
		$scope.returnCar = function(){
			var plate, jmbg;
			plate = angular.copy($scope.plate);
			jmbg = angular.copy($scope.jmbg);
			ServiceCar.returnCar(plate, jmbg).then(function(response){
				var res = response.data;
				console.log(res);
				$scope.validate = res;
			});
		};
	}])
	.controller('viewCarCtrl', ['$scope','ServiceCar', function($scope, ServiceCar) {
		$scope.car = {};
		$scope.validate; 
		$scope.showForm = false;
		$scope.showBtn = false;
		$scope.viewCar = function(){
			var car;
			car = angular.copy($scope.car);
			ServiceCar.view(car.plate).then(function(response){
				var res = response.data;
				console.log(res);
				$scope.validate = res;
			});
		};
	}])
	.factory('ServiceCar', ['$http', function($http) {
		var service = {};
		
		service.add = function(car) {
			console.log(parseInt(car.category));
			return $http.get('http://localhost:8080/Client/rest/rent/car/add', {params :{"plate":car.plate, "category":parseInt(car.category) }});
		}
		
		service.remove = function(plate) {
			return $http.get('http://localhost:8080/Client/rest/rent/car/remove', {params : {"plate":plate}});
		}
		
		service.list = function() {
			return $http.get('http://localhost:8080/Client/rest/rent/car/list');
		}
		
		service.rent = function(plate, jmbg) {
			return $http.get('http://localhost:8080/Client/rest/rent/car/rent', {params :{"plate":plate, "jmbg":jmbg }});
		}
		
		service.returnCar = function(plate, jmbg) {
			return $http.get('http://localhost:8080/Client/rest/rent/car/return', {params :{"plate":plate, "jmbg":jmbg }});
		}
		
		service.view = function(plate) {
			return $http.get('http://localhost:8080/Client/rest/rent/car/view/' + plate);
		}
		
		return service;
	}])
	.controller('addCustomerCtrl', ['$scope','ServiceCustomer', function($scope, ServiceCustomer) {
		$scope.customer = {};
		$scope.validate; 
		$scope.showForm = false;
		$scope.showBtn = false;
		$scope.addCustomer = function(){
			var customer;
			customer = angular.copy($scope.customer);
			ServiceCustomer.add(customer).then(function(response){
				var res = response.data;
				console.log(res);
				$scope.validate = res;
			});
		};
	}])
	.controller('removeCustomerCtrl', ['$scope','ServiceCustomer', function($scope, ServiceCustomer) {
		$scope.customer = {};
		$scope.validate; 
		$scope.showForm = false;
		$scope.showBtn = false;
		$scope.removeCustomer = function(){
			var customer;
			customer = angular.copy($scope.customer);
			ServiceCustomer.remove(customer.jmbg).then(function(response){
				var res = response.data;
				console.log(res);
				$scope.validate = res;
			});
		};
	}])
	.controller('listCustomerCtrl', ['$scope','ServiceCustomer', function($scope, ServiceCustomer) {
		$scope.showForm = false;
		$scope.showBtn = false;
		$scope.listCustomer = function(){
			ServiceCustomer.list().then(function(response){
				var res = response.data;
				console.log(res);
				$scope.validate = res;
			});
		};
	}])
	.controller('viewCustomerCtrl', ['$scope','ServiceCustomer', function($scope, ServiceCustomer) {
		$scope.customer = {};
		$scope.validate; 
		$scope.showForm = false;
		$scope.showBtn = false;
		$scope.viewCustomer = function(){
			var customer;
			customer = angular.copy($scope.customer);
			ServiceCustomer.view(customer.jmbg).then(function(response){
				var res = response.data;
				console.log(res);
				$scope.validate = res;
			});
		};
	}])
	.factory('ServiceCustomer', ['$http', function($http) {
		var service = {};
		
		service.add = function(customer) {
			console.log(parseInt(customer.category));
			return $http.get('http://localhost:8080/Client/rest/rent/customer/add', {params :{"jmbg":customer.jmbg, "category":parseInt(customer.category) }});
		}
		
		service.remove = function(jmbg) {
			return $http.get('http://localhost:8080/Client/rest/rent/customer/remove', {params : {"jmbg":jmbg}});
		}
		
		service.list = function() {
			return $http.get('http://localhost:8080/Client/rest/rent/customer/list');
		}
		
		service.view = function(jmbg) {
			return $http.get('http://localhost:8080/Client/rest/rent/customer/view/' + jmbg);
		}
		
		return service;
	}]);


