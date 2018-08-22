import Vue from 'vue';
import App from './components/App.vue';
import router from './router'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faEye } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(faEye)

Vue.component('font-awesome-icon', FontAwesomeIcon)

new Vue({
    el: '#app',
    router,
    components: { App },
    template: '<App/>'
})

import '../sass/style.scss';