Vue.component('menu-item', {
    props: ['item'],
    template: '<a v-bind:href="link" class="nav--item">{{ item }}</a>',
    computed: {
        link() {return '#' + this.item.toLowerCase().replace(/ /g, '-')},
    }
});

Vue.component('page-content', {
    template: '<section class="section--text"><p><slot></slot></p></section>'
});

Vue.component('publications', {
    template: '<ul><slot></slot></ul>'
});

Vue.component('publication', {
    props: ['name', 'type', 'abstract', 'authors', 'pubdata'],
    template: '<li>{{ name }}</li>'
});

var app = new Vue({
        el: '.app',
        data: {
            currentRoute: window.location.hash || '#about-me',
        },
        methods: {
          addPublication: (item) => this.publications.push(item)
        }
});

window.addEventListener('popstate', () => {
  app.currentRoute = window.location.hash
});