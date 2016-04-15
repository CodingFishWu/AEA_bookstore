'use strict';

const baseUrl = '/Bookstore';

angular.module('resources', ['ngResource'])

.factory('UserService', ['$resource', function($resource) {
	return $resource(baseUrl + '/user', {},
	{
		login: {
			method: 'POST',
			url: baseUrl + '/user',
			params: {
				action: 'login'
			},
			isArray: false
		},
		signup: {
			method: 'POST',
			url: baseUrl + '/user',
			params: {
				action: 'register'
			},
			isArray: false
		},
		logout: {
			method: 'POST',
			url: baseUrl + '/user',
			params: {
				action: 'logout'
			},
			isArray: false
		},
		query: {
			method: 'GET',
			url: baseUrl + '/admin',
			params: {
				target: 'user',
				action: 'list'
			},
			isArray: true
		}
	});
}])

/*
**  location service
*/

.factory('BookService', ['$resource', function($resource) {
	return $resource(baseUrl + '/book', {},
	{
		query: {
			method: 'GET',
			url: baseUrl + '/book',
			params: {
				action: 'list'
			},
			isArray: true
		},
		update: {
			method: 'PUT',
			url: baseUrl + '/admin',
			params: {
				target: 'book',
				action: 'update'
			},
			isArray: false
		},
		get: {
			method: 'GET',
			url: baseUrl + '/book',
			params: {
				action: 'info'
			},
			isArray: false
		},
		delete: {
			method: 'DELETE',
			url: baseUrl + '/admin',
			params: {
				target: 'book',
				action: 'delete'
			},
			isArray: false
		},
		add: {
			method: 'POST',
			url: baseUrl + '/admin',
			params: {
				target: 'book',
				action: 'add'
			}
		}
	});
}])

.factory('CartService', ['$resource', function($resource) {
	return $resource(baseUrl + '/cart', {},
	{
		get: {
			method: 'GET',
			url: baseUrl + '/cart',
			isArray: true
		},
		addBook: {
			method: 'PUT',
			url: baseUrl + '/cart',
			params: {
				action: 'addBook'
			},
			isArray: false
		},
		deleteBook: {
			method: 'PUT',
			url: baseUrl + '/cart',
			params: {
				action: 'deleteBook'
			},
			isArray: false
		},
		toOrder: {
			method: 'PUT',
			url: baseUrl + '/cart',
			params: {
				action: 'toOrder'
			},
			isArray: false
		}
	});
}])
.factory('OrderService', ['$resource', function($resource) {
	return $resource(baseUrl + '/order', {},
	{
		query: {
			method: 'get',
			url: baseUrl + '/order',
			params: {
				action: 'list'
			},
			isArray: true
		}
	});
}]);
