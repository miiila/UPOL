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

Vue.component('filtered-list', {
    props: ['items', 'filteroptions', 'filterkey'],
    data() {return {selectedTypes: [allTypes]}},
    template: `<div>
                <filter-inputs @update="selectedTypes = $event" :items="filteroptions" :selectedTypes="selectedTypes" ></filter-inputs>
                    <ul>
                        <filter-item v-for="item in items" :item="item" :key="item.name" v-show="toShow(item[filterkey])"></filter-item>
                    </ul>
                </div>`,
    methods: {
        toShow(type) {
            return this.selectedTypes.indexOf(allTypes) > -1 || this.selectedTypes.indexOf(type) > -1;
        }
    }
});

Vue.component('filter-item', {
    props: ['item'],
    template: '<li>{{item.type}}: {{ item.name }}</li>'
});

Vue.component('filter-inputs', {
    props: ['items'] ,
    data() {return {selected: []}},
    methods: {
        change() {this.$emit('update', this.selected)}
    },
    template: `<form>
                <template v-for="item in items">
                    <input type="radio" :id="item" :value="item" v-model="selected" @click="change()"><label :for="item">{{item}}</label>
                </template>
               </form>`
});

const allTypes = 'All types';

const app = new Vue({
    el: '.app',
    data: {
        currentRoute: window.location.hash || '#about-me',
        publications: [],
        publicationTypes: [allTypes]
    }
});

window.addEventListener('popstate', () => {
  app.currentRoute = window.location.hash
});