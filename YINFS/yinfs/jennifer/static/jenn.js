timePeriodSort = (a, b) => {
    return (b.yearStart - a.yearStart);
};

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
            return this.selectedType === allTypes || this.selectedType === type;
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

Vue.component('teaching', {
    props: ['courses'],
    data() {
        return {
            details: false
        }
    },
    template:`
        <div class="teaching">
            <div class="timeline">
                <span class="timeline__title">Current teaching</span>
                <hr>
                <table>
                    <teaching-details v-for="item in activeCourses" :item="item"> 
                    </teaching-details>
                </table>
            </div>
            <div class="timeline">
                <span class="timeline__title">Teaching History</span>
                <hr>
                <table>
                    <timeline-name-with-details v-for="item in inactiveCourses" :item="item" :past="true"> 
                    </timeline-name-with-details>
                </table>
            </div>
        </div>
    `,
    computed: {
      activeCourses() {
          return this.courses.filter((course) => {return course.active}).sort(timePeriodSort);
      },
      inactiveCourses() {
          return this.courses.filter((course) => {return !course.active}).sort(timePeriodSort);
      }
    },
});

Vue.component('timeline-name-with-details', {
    props: ['item', 'past'],
    data() {
        return {
            showDetails: false
        }
    },
    template:`
        <tr class="timeline__row">
            <td class="timeline__period" :class="{'timeline__period--passsive': this.past}"> {{ item.yearStart }} - {{ item.yearEnd || "present" }}</td>
            <td class="teaching__name"><span class="timeline__details" :class="{'timeline__details--hidden': !this.showDetails, 'timeline__details--shown': this.showDetails}" @click="showDetails=!showDetails">{{ item.name }}</span><span class="teaching__details" v-show="showDetails"><br/>{{ item.description }}</span> </td>
        </tr>  
    `
});

Vue.component('education', {
    props: ['items'],
    computed: {
        sorted() {
            return this.items.sort(timePeriodSort)
        }
    },
    template:`
        <div class="education">
            <span class="timeline__title">Education</span>
            <hr>
            <table>
                <tr v-for="item in sorted" class="timeline__row">
                    <td class="timeline__period"> {{ item.graduationYear }}</td>
                    <td class="positions__name">{{ item.name }}<br /> <span class="positions__location" >{{ item.university }}, {{ item.faculty }}</span></td> 
                </tr>
            </table>
        </div>
    `
});

Vue.component('positions', {
    props: ['items'],
    computed: {
        sorted() {
            return this.items.sort(timePeriodSort)
        }
    },
    template:`
        <div class="timeline">
            <span class="timeline__title">Academic Positions</span>
            <hr>
            <table>
                <tr v-for="item in sorted" class="timeline__row">
                    <td class="timeline__period"> {{ item.yearStart }} - {{ item.yearEnd || "present" }}</td>
                    <td class="positions__name">{{ item.name }}<br /> <span class="positions__location" >{{ item.university }}</span></td> 
                </tr>
            </table>
        </div>
    `
});

Vue.component('awards', {
    props: ['items'],
    computed: {
        sorted() {
            return this.items.sort(timePeriodSort)
        }
    },
    methods: {
        getYearString(year) {
            if (year) {
                return " - " + year;
            } else {
                return "";
            }
        }
    },
    template:`
        <div class="timeline">
            <span class="timeline__title">Honors, Awards and Grants</span>
            <hr>
            <table>
                    <timeline-name-with-details v-for="item in sorted" :item="item"> 
                    </timeline-name-with-details>
            </table>
        </div>
    `
});

const allTypes = 'All types';

const app = new Vue({
    el: '.app',
    data: {
        currentRoute: window.location.hash || '#about-me',
        publications: [],
        publicationTypes: [allTypes],
        teachingClasses: [],
        positions: [],
        education: [],
        awards: []
    }
});

window.addEventListener('popstate', () => {
  app.currentRoute = window.location.hash
});