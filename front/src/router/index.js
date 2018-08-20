import Vue from 'vue'
import Router from 'vue-router'
import AnnoyIndexes from '../components/AnnoyIndexes.vue'
import AnnoyIndexesExplorer from '../components/AnnoyIndexesExplorer.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/annoy-indexes',
      name: 'AnnoyIndexes',
      component: AnnoyIndexes
    },
    {
      path: '/ann-indexes-explorer',
      name: 'AnnoyIndexesExplorer',
      component: AnnoyIndexesExplorer
    }
  ]
})