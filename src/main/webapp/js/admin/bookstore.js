const IMG_QUESTION_MARK = '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';
const IMG_HEADER = 'data:image/png;base64,';
const SERVER_IP = 'localhost';
const SERVER_PORT = 7001;
const API_BASE_URL = 'http://' + SERVER_IP + ':' + SERVER_PORT + '/bookstore/api/v1';
const WS_URL = 'ws://' + SERVER_IP + ':' + SERVER_PORT + '/bookstore/api/v1/purchase';
const TOASTR_CONFIG = {
        debug: false,
        timeOut: 500,
        extendedTimeOut: 100,
        fadeIn: 25,
        fadeOut: 25,
        positionClass: 'toast-top-right'
    };
    
let toImage = (data) => {
	if (data === null || data === undefined) 
	   return IMG_HEADER + IMG_QUESTION_MARK;
	let array = data.split(',');
	if (array.length === 2) {
		return data;
	}
	return IMG_HEADER + data;
};

let toImageData = (img) => {
	let array = img.split(',');
	if (array.length == 2) {
		return array[1];
	}
	return img;
};

class Book {
	constructor(book) {
		this.isbn = ko.observable(book.isbn);
		this.author = ko.observable(book.author);
		this.title = ko.observable(book.title);
		this.price = ko.observable(book.price);
		this.cover = ko.observable(toImage(book.cover));
		this.pages = ko.observable(book.pages);
		this.year = ko.observable(book.year);
	}

	update = (book) => {
		for (let prop in book) {
			if (!this.hasOwnProperty(prop)) continue;
			if (ko.isComputed(this[prop])) continue;
			if (ko.isObservable(this[prop]))
				this[prop](book[prop]);
			else
				this[prop](book[prop]);
		}
		this.cover(toImage(book.cover));
	};

	toJSON = () => {
		return JSON.stringify({
			isbn: this.isbn(),
			author: this.author(),
			title: this.title(),
			cover: toImageData(this.cover()),
			price: this.price(),
			year: this.year(),
			pages: this.pages()
		});
	};

};

let emptyBook = () => {
	return new Book({
		isbn: "",
		author: "",
		title: "",
		price: 0,
		cover: IMG_QUESTION_MARK,
		year: 2017,
		pages: 100
	});
};

class BookViewModel {
	constructor() {
		this.book = emptyBook();
		this.books = ko.observableArray([]);
		this.status = ko.observable();
		this.data = {
			labels: ko.observableArray([]),
			datasets: [
				{
					label: ['Amount'],
					backgroundColor: "rgba(0,0,220,0.2)",
					borderColor: "rgba(0,0,180,1)",
					pointColor: "rgba(0,0,140,1)",
					pointStrokeColor: "blue",
					pointHighlightFill: "darkblue",
					pointHighlightStroke: "rgba(220,220,220,1)",
					data: ko.observableArray([])
				}
			]
		};
		this.counter = 0;
		// Socket variables
		this.socket = new WebSocket(WS_URL);
		this.socket.onopen = () => {
			toastr.success("Connected!", "", TOASTR_CONFIG);
		};
		this.socket.onmessage = (message) => {		// logo related features
			let amount = JSON.parse(message.data).sum;
			this.data.datasets[0].data.push(Number(amount));
			this.data.labels.push(this.counter);
			this.counter++;
		}
		this.fileData = ko.observable({
			dataUrl: ko.observable()
		});
	}

	displayBook = (book) => {
		this.book.update(book);
		this.fileData().dataUrl(this.book.cover());
	};

	insertFile = (e) => {
		e.preventDefault();
		var files = e.target.files || e.originalEvent.dataTransfer.files;
		var reader = new FileReader();
		reader.readAsDataURL(files[0]);
		reader.onload = (event) => {
			this.fileData().dataUrl(event.target.result);
			this.book.cover(this.fileData().dataUrl());
		};
	};
	dragover = (e) => {
		e.preventDefault();
	};

	/*
	 * Finds the stock by symbol
	 */
	findByIsbn = () => {
		get(API_BASE_URL.concat('/books/').concat(this.book.isbn()),
			(book) => {
				this.book.update(book);
				this.fileData().dataUrl(toImage(book.cover));
			}
		);
	};

	removeBookByIsbn = (isbn) => {
		del(API_BASE_URL.concat("/books/").concat(isbn),
			(book) => {
				this.book.update(book);
				this.fileData().dataUrl(this.book.cover());
			},
			(xhr) => {
				this.status(xhr.responseText.message);
			}
		);
	};

	/*
	 * Removes the given book
	 */
	removeBook = (book) => {
		this.removeBookByIsbn(book.isbn());
		this.books.remove(book);
	};

	/*
	 * Deletes the book in the view model
	 */
	deleteBook = () => {
		this.removeBookByIsbn(this.book.isbn());
	};

	/*
	 * Retrieves all books
	 */
	findAll = () => {
		get(API_BASE_URL.concat("/books"),
			(books) => {
				this.books([]);
				for (var i in books) {
					this.books.push(new Book(books[i]));
				}
			}
		);
	};

	add = () => {
		this.book.cover(this.fileData().dataUrl());
		post(API_BASE_URL.concat("/books"), 'application/json', this.book.toJSON(),
			() => {
				this.findAll();
			}
		);
	};

	updateBook = (book) => {
		put(`${API_BASE_URL}/books/${book.isbn}`, 'application/json', book.toJSON(),
			() => {
				this.findAll();
			}
		);
	};

	update = () => {
		this.book.cover(this.fileData().dataUrl());
		put(`${API_BASE_URL}/books/${this.book.isbn()}`, 'application/json', this.book.toJSON(),
			() => {
			}
		);
	};
};

let vm = new BookViewModel();

$('document').ready(() => {
	ko.applyBindings(vm);
});