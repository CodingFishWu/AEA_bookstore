'use strict'
class ManageCtrl {
	constructor($state) {
		this.$state = $state;
	}
}

class BookManageCtrl {
	constructor($state, $uibModal, BookService) {
		this.$state = $state;
		this.$uibModal = $uibModal;
		this.BookService = BookService;

		this.getBooks();
	}

	getBooks() {
		this.books = this.BookService.adminQuery();
	}

	update(index) {
		let self = this;
		self.$uibModal.open({
				templateUrl: 'views/manage/bookModal.html',
				controller: 'bookModalCtrl as ctrl',
				resolve: {
					book: self.books[index]
				}
			})
			.result.then(function() {
			// 防止添加完以后立即修改导致id不存在，所以必须重新获取
			self.getBooks();
		});
	}

	delete(index) {
		let self = this;
		self.BookService.delete({id: self.books[index].id}, {}, function() {
			self.getBooks();
		});
	}

	add() {
		let self = this;
		self.$uibModal.open({
			templateUrl: 'views/manage/bookModal.html',
			controller: 'bookModalCtrl as ctrl',
			resolve: {
				book: null
			}
		})
		.result.then(function() {
			// 防止添加完以后立即修改导致id不存在，所以必须重新获取
			self.getBooks();
		});
	}
}

class BookModalCtrl {
	constructor($uibModalInstance, BookService, book) {
		this.$uibModalInstance = $uibModalInstance;
		this.BookService = BookService;
		this.book = book;

		if (book == null) {
			this.title = "增加图书";
		}
		else {
			this.title = "修改图书";
			this.id = book.id;
			this.name = book.name;
			this.author = book.author;
			this.price = book.price;
			this.stock = book.price;
			this.category = book.category;
		}
	}

	submit() {
		let self = this;
		let newBook = new this.BookService({
			id: self.id,
			name: self.name,
			author: self.author,
			price: self.price,
			stock: self.stock,
			category: self.category
		});
		
		if (self.book == null) {
			newBook.$add(function(data) {
				alert('已提交');
				self.$uibModalInstance.close();
			});
		}
		else {
			newBook.$update({id: self.id}, function(data) {
				alert('已修改');
				self.$uibModalInstance.close();
			});
		}
	}
}

class UserManageCtrl {
	constructor($state, UserService) {
		this.$state = $state;
		this.UserService = UserService;

		this.getUsers();
	}

	getUsers() {
		let self = this;
		self.UserService.query(function(result) {
			self.users = result;
		});
	}
}

angular.module('manageModule', [])
.controller('manageCtrl', ['$state', ManageCtrl])
.controller('bookManageCtrl', ['$state', '$uibModal', 'BookService', BookManageCtrl])
.controller('bookModalCtrl', ['$uibModalInstance', 'BookService', 'book', BookModalCtrl])
.controller('userManageCtrl', ['$state', 'UserService', UserManageCtrl])