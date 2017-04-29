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
    props: ['items', 'filteroptions', 'filterkey', 'title'],
    data() {return {selectedType: allTypes}},
    template: `<div class="filter">
                <span class="filter__title">{{ title }}</span>
                <filter-inputs @update="selectedType = $event" :items="filteroptions" :selectedType="selectedType" ></filter-inputs>
                    <ul class="filter__content">
                        <filter-item v-for="item in items" :item="item" :key="item.name" v-show="toShow(item[filterkey])"></filter-item>
                    </ul>
                </div>`,
    methods: {
        toShow(type) {
            return this.selectedType == allTypes || this.selectedType == type;
        }
    }
});

Vue.component('filter-item', {
    props: ['item'],
    template: '<li>{{item.type}}: {{ item.name }}</li>'
});

Vue.component('filter-inputs', {
    props: ['items'] ,
    data() {return {selected: allTypes}},
    methods: {
        change() {this.$emit('update', this.selected)}
    },
    template: `<select class="filter__select" v-model="selected" @change="change()">
                    <template v-for="item in items">
                            <option :value="item">{{item}}</option>
                    </template>
               </select>`
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