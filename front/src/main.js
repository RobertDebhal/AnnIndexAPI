import Vue from 'vue';
import App from './components/App.vue';
import router from './router'

new Vue({
    el: '#app',
    router,
    components: { App },
    template: '<App/>'
})

import '../sass/style.scss';