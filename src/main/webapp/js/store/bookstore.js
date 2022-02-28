const SERVER_IP = 'localhost';
const SERVER_PORT = 7001;
const API_BASE_URL = 'http://' + SERVER_IP + ':' + SERVER_PORT + '/bookstore/api/v1';
const TOASTR_CONFIG = {
        debug: false,
        timeOut: 500,
        extendedTimeOut: 100,
        fadeIn: 25,
        fadeOut: 25,
        positionClass: 'toast-top-right'
    };
    
class Book {
	constructor(book) {
		this.isbn = book.isbn;
		this.author = book.author;
		this.title = book.title;
		this.price = book.price;
		this.year = book.year;
	}
};

class BookItem {
	constructor(book, quantity) {
		this.book = book;
		this.quantity = ko.observable(quantity);
		this.subTotal = ko.computed(() => {
			return this.quantity() * this.book.price;
		});
	}
};

class BasketViewModel {
	constructor() {
		// observable model
		this.books = ko.observableArray([]);
		this.bookItems = ko.observableArray([]);
		this.totalAmount = ko.computed(() => {
			let sum = 0.;
			for (let item of this.bookItems()) {
				sum += item.subTotal();
			}
			return sum;
		});
		this.quantity = ko.computed(() => {
			let q = 0;
			for (let item of this.bookItems()) {
				q += Number(item.quantity());
			}
			return q;
		});
		get(API_BASE_URL.concat("/books"),
			(books) => {
				this.books(books);
			}
		);
	}


	addToBasket = (book) => {
		for (let item of this.bookItems()) {
			if (item.book.isbn === book.isbn) {
				item.quantity(Number(item.quantity()) + 1);
				return;
			}
		}
		this.bookItems.push(new BookItem(book, 1));
	};

	purchaseOrder = () => {
		var items = [];
		for (let bookItem of this.bookItems()) {
			items.push({
				isbn: bookItem.book.isbn,
				quantity: bookItem.quantity()
			});
		}
		post(
			API_BASE_URL.concat("/orders"),
			"application/json",
			JSON.stringify({ items: items }),
			() => {
				toastr.success("Books are purchased!", "", TOASTR_CONFIG);
				this.bookItems([]);
			}
		);
	};

	quantityChanged = (item) => {
		if (item.quantity() == 0)
			this.bookItems.remove(item);
	};

	removeFromBasket = (item) => {
		this.bookItems.remove(item);
	};

};

let basketViewModel = new BasketViewModel();

$(document).ready(() => {
	ko.applyBindings(basketViewModel);
});