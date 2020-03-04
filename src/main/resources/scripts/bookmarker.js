const bookmarkPromise = () => {
    return fetch('/api/v1/bookmarks', {
        headers: {
            'Content-Type': 'application/json',
            'Accepts': 'application/json',
        },
    });
};

// app Vue instance
const app = new Vue({
    // app initial state
    data: {
        bookmarks: [],
        newBookmark: "",
        editedBookmark: null,
        visibility: "all"
    },

    mounted() {
        bookmarkPromise()
            .then(res => res.json())
            .then(json => {
                json.forEach(bookmark => this.bookmarks.push(bookmark))
            })
            .catch(err => {
                console.log("throwing error:" + err)
            });
    },

    // watch bookmarks change for localStorage persistence
    watch: {},

    // computed properties
    // http://vuejs.org/guide/computed.html
    computed: {},

    filters: {},

    // methods that implement data logic.
    // note there's no DOM manipulation here at all.
    methods: {},

    // a custom directive to wait for the DOM to be updated
    // before focusing on the input field.
    // http://vuejs.org/guide/custom-directive.html
    directives: {}
});

// mount
app.$mount(".bookmarkapp");
