app.controller('AppController',['$scope', '$http', function($scope,$http){
	var url = "http://localhost:8080/api/book";
	$scope.books = [];
	$scope.loadAll = function() {
		$http.get(url).then(function (response){
			$scope.books = response.data;
	     },function (error){
	    	 alert('not found data');
	     });
	};
	
	$scope.loadAll();
	
	$scope.book = {};
	
	$scope.addBook = function(book) {
		$http.post(url, book).then(function(data, status) {
			console.log(status);
			$scope.loadAll();
			$scope.clear();
		}, function(error){
			
		});
		
	}
	
	$scope.deleteBook = function(id){
		$http.delete(url+"/" + id).then(function(data, status) {
			console.log(data);
			$scope.loadAll();
		},function(error){
			
		});
	}
	
	$scope.update = function (id,book){
		$http.put(url + "/" + id,book).then(function(data, status) {
			console.log(data);
			$('#editBook').modal('hide');
			$scope.loadAll();
			$scope.clear();
		}, function(error){
			
		});
	}
	
	$scope.showEditBook = function(id){
		$http.get(url+"/"+id).then(function(response) {
			$scope.book = response.data;
			$('#editBook').modal('show');
		});
	}
	
	$scope.clear = function (){
		$scope.book = {id : null, name : null, author : null};
	}
}]);