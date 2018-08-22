<template>
<div class="main">

    <section>
        <div class="sub_container">
        <div class="row center">
            <div class="header">
                <p> Source vector </p>
            </div>
        </div>
        <div class="row center">
            <!-- <div class="col-md-2">
                <label for="banner"> banner </label>
                <select id="banner" v-model="banner" class="form-control">
                    <option disabled value="gilt"> Gilt </option>
                </select>
            </div> -->
            <div class="col-md-3">
                <label for="model"> model </label>
                <select id="model" v-model="model" class="form-control">
                    <option disabled value="">Please select one</option>
                    <option v-for="model in models"> {{model}} </option>
                </select>
            </div>
            <div class="col-md-3">
                <label for="index"> annoy index </label>
                <select id="index" v-model="index" class="form-control">
                    <option value="">Please select one</option>
                    <option v-for="index in indexes">
                        {{index}}
                    </option>
                </select>
            </div>
            <div class="col-md-3">
                <label for="index_key"> vector key </label>
                <input id="index_key" v-model="index_key" @keyup.enter="getAnnoyVector" class="form-control" type="text">
            </div>
            
            <div class="col-md-2" id="go">
                <div class="row center">
                    <div>
                        <label for="fast_update_checkbox">fast update</label>
                        <input id="fast_update_checkbox" type="checkbox" v-model="fast_update" class="form-control" title="If checked it will be populated automatically with looked up user or product id">
                    </div>
                </div>
            
                <div class="row">
                    <button id="button" @click="getAnnoyVector"> Go! </button>
                </div>
            </div>
        </div>
        </div>
    <div class="sub_container">           


        <div class="row">
            <hr>
        </div>
        
        <div class="row center">
                <div class="header">
                    <p> Index Vector </p>
                </div>
                <textarea  id="index_vector" v-model="index_vector" class="form-control" placeholder="index vector" rows="5" disabled></textarea>
            </div>
    </div>
    </section>


 <template v-if="index_vector.length > 0"> 
     <div class="sub_container">
        <div class="row">
            <hr>
        </div>

        <div class="row center">
            <div class="header">
                <p> Target index </p>
            </div>
        </div>

        <div class="row center">
            <div class="col-md-3">
                 <label for="index_target"> annoy index </label>
                 <select id="index_target" v-model="index_target" class="form-control">
                     <option value="">Please select one</option>
                     <option v-for="index in indexes"> {{index}} </option>
                 </select>
             </div>
             <div class="col-md-3">
                 <label for="k"> k </label>
                 <input id="k" v-model="k" class="form-control" type="number">
             </div>
             <div class="col-md-3">
                 <label for="neighbor_type"> neighbor type </label>
                 <select id="neighbor_type" v-model="neighbor_type" class="form-control">
                     <option value="">Unknown</option>
                     <option v-for="type in neighbor_types"> {{type}} </option>
                 </select>
             </div>
             <div class="col-md-2" id="bottom_button">
                 <button @click="findNeighbors" :disabled="index_target.trim().length == 0"> Go! </button>
             </div>
         </div>
     </div>
    </template>

    <template v-if="neighbors.length > 0">

        <div class="row">
            <hr>
        </div>

        <div class="row">
            <h1> Neighbors <i class="fa fa-eye" @click="show_raw_neighbors = !show_raw_neighbors" title="toggle on/off the raw results"> </i> </h1>
         </div>

        <div class="row" v-if="show_raw_neighbors">
            <ul>
                <li v-for="neighbor in neighbors"> {{ neighbor }}</li>
            </ul>
        </div>

    </template>

    <template v-if="neighbors_data.length > 0 && neighbor_type == 'brand' ">

        <div class="row">
            <h2> Brands </h2>
        </div>
        <div class="row">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">id</th>
                    <th scope="col">key</th>
                    <th scope="col">name</th>
                    <th scope="col">image (opt)</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(entry, index) in neighbors_data">
                    <th scope="row"> {{ index + 1}} </th>
                    <td>
                        {{ entry.id }}
                    </td>
                    <td>
                        {{ entry.key }}
                    </td>
                    <td>
                        {{ entry.name }}
                    </td>
                    <td>
                        <template v-if="entry.image">
                            <img v-bind:src="entry.image | giltProductImage">
                        </template>
                        <template v-else>
                            &nbsp;
                        </template>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

    </template>

    <template v-if="neighbors_data.length > 0 && ['product', 'product-look'].includes(neighbor_type.trim())">

        <div class="row">
            <h2> Products </h2>
        </div>
        <div class="row" v-for="i in parseInt(neighbors_data.length / 6 + 1)">
            <div class="col-md-2" v-for="j in 6">
                <template v-if="( j - 1 ) + 6 * ( i - 1 ) < neighbors_data.length">
                    <product-look
                            :product-image="neighbors_data[( j - 1 ) + 6 * ( i - 1 )].product_image"
                            :pdp-url="neighbors_data[( j - 1 ) + 6 * ( i - 1 )].product_detail_page"
                            :product-name="neighbors_data[( j - 1 ) + 6 * ( i - 1 )].product_name"
                            :brand="neighbors_data[( j - 1 ) + 6 * ( i - 1 )].brand_name"
                    ></product-look>
                </template>
                <template v-else>
                    &nbsp;
                </template>
            </div>
        </div>

    </template>

</div>

</template>

<script>
import qs from 'qs'
import ProductLook from './ProductLook.vue'
import UserIdentification from './UserIdentification.vue'
import axios from 'axios'
export default {
  name: "ann-indexes-explorer",
  components: {
    UserIdentification, ProductLook
  },
  data() {
    return {
        banner: 'gilt',
        model: '',
        models: [],
        indexes: [],
        index: '',
        index_target: '',
        index_key: '',
        fast_update: true,
        index_vector: [],
        k: 10,
        neighbor_types: ['product', 'product-look', 'brand'],
        neighbor_type: '',
        neighbors: [],
        neighbors_ids: [],
        neighbors_data: [],
        show_raw_neighbors: true
    }
  },
  methods: {
    getModelIndexes: function(input) {
        let vm = this
        axios
            .get('/ann_indexes')
            .then(function(response) {
                vm.indexes = response.data[vm.model]
            })
    },
    getAnnoyVector: function() {
        let vm = this
        axios
            .post('/ann_indexes/' + vm.index.trim(), {
                id: vm.index_key,
                ann_key_type: "StringAnnKey"
            })
            .then(function(response) {
                vm.index_vector = response.data
            })
    },
    findNeighbors: function() {
        let vm = this
        axios
            .post('/ann_indexes/' + vm.index.trim() + '/search', {
                k: parseInt(vm.k),
                search_k: -1,
                ids: [{id:vm.index_key,ann_key_type: "StringAnnKey"}],
                vectors: [vm.index_vector]
            })
            .then(function(response) {
                vm.neighbors = response.data
                if (vm.neighbor_type != '') {
                    vm.parseIdsFromNeighbors()
                }
            })
    },
    setUserId: function(userId) {
        if (this.fast_update) {
            this.index_key = userId
        }
    },
    parseIdsFromNeighbors: function() {
        var numberPattern = /\d+/g;
        this.neighbors_ids = this.neighbors.map(neighbor => neighbor.match(numberPattern)[0])
    },
    getBrandData: function() {
        let vm = this
        axios
            .get('/brands', {
                params: {
                    brand_ids: vm.neighbors_ids
                },
                paramsSerializer: function(params) {
                    return qs.stringify(params, {arrayFormat: 'repeat'})
                }
            })
            .then(function(response) {
                vm.neighbors_data = response.data
                vm.show_raw_neighbors = false
            })
    },
    getProductData: function() {
        let vm = this
        axios
            .get('/product_looks', {
                params: {
                    product_ids: vm.neighbors_ids
                },
                paramsSerializer: function(params) {
                    return qs.stringify(params, {arrayFormat: 'repeat'})
                }
            })
            .then(function(response) {
                vm.neighbors_data = response.data
                vm.show_raw_neighbors = false
            })
    },
    getProductLookData: function() {
        console.log('inside getProductLookData')
        let vm = this
        axios
            .get('/product_looks', {
                params: {
                    product_look_ids: vm.neighbors_ids
                },
                paramsSerializer: function(params) {
                    return qs.stringify(params, {arrayFormat: 'repeat'})
                }
            })
            .then(function(response) {
                vm.neighbors_data = response.data
                vm.show_raw_neighbors = false
            })
    }
  },
  watch: {
    model: function() {
        if (model.length == 0) {
            this.indexes = []
            return
        } else {
            this.getModelIndexes(this.model)
        }
    },
    index_key: _.debounce(
        function () {
            if (this.index != '' && this.index_key.length > 0) {
                this.getAnnoyVector()
            }
        },
        1000
    ),
    neighbors_ids: function () {
        console.log('(' + this.neighbor_type + ')')
        if (this.neighbors_ids.length > 0 && this.neighbor_type == 'brand') {
            this.getBrandData()
        }
        if (this.neighbors_ids.length > 0 && this.neighbor_type == 'product') {
            this.getProductData()
        }
        if (this.neighbors_ids.length > 0 && this.neighbor_type == 'product-look') {
            console.log('loading product look data')
            this.getProductLookData()
        }
    }
  },
  created: function () {

        let vm = this
        axios
            .get('/ann_indexes')
            .then(function(response) {
                vm.models = Object.keys(response.data);
            })
    },
  filters: {
    giltProductImage: function (value) {
      return 'https://www.gilt.com' + value
    }
  }
}
</script>
<style scoped>

input[type="checkbox"] {
  height: auto;
  width: auto;
  margin: 5px 5px 5px 5px;
}

#fast_update {
    display:block;
}

#fast_update span {
    float:left;
    margin-left: 10px;
}

.center {
    margin: auto;
    text-align: center;
    padding-top: 5px;
    width: 90%;
}

.center div {
    margin: auto;
    text-align: center;
    padding-top: 5px;
}

.center div button{
    margin: auto;
    text-align: center;
    width: 200px;
}

.center h2{
    margin: auto;
    text-align: center;
    
}

.left {
    margin: auto;
    text-align: left;
    padding: 5px 0 0 5px;
}

.sub_container {
  border: 1px solid #ddd;
  padding: 0px 20px 20px 20px;
  border-radius: 5px;
  margin: 20px 20px 0px 20px;
  background-color: white;
  min-height: 300px;
  overflow: scroll; 
  background: rgb(253,253,253);
}

.header {
    font-family: Roboto, "Helvetica Neue", Arial, sans-serif;
    font-size:22px;
    font-weight:300;
    color: rgb(51, 51, 51);
    float:left;
    padding-top:10px;
}

.header p {
    border-bottom-color: rgba(0, 0, 0);
    border-bottom-style: solid;
    border-bottom-width: 1px;
}

.top_header {
  height: 55px;
  width: 100%;
  font-family: Roboto, "Helvetica Neue", Arial, sans-serif;
  font-size: 20px;
  font-weight: 200;
}

.top_header p {
    float: left;
    padding: 19px 16px;
}

.top_row {
    border-top: 1px solid #ddd;
}

.main {
  height: 1276px;
  margin-left: 240px; /* Same as the width of the sidebar */ 
}

#go {
    margin: 0;
}

#go input {
    float: right;
}

#go label {
    padding-bottom: 5px;
    margin: auto;
}

#bottom_button {
    margin-bottom: 0;
}

#index_vector {
    height: 200px;
    margin-bottom: 50px;
    background: linear-gradient(to bottom right, #F5F5F5 0%, rgb(241, 241, 241) 100%);
}

select {
    background: linear-gradient(to bottom, #F5F5F5 0%, #E8E8E8 100%);
}

input[type="text"], input[type="number"] {
    background: linear-gradient(to bottom, #F5F5F5 0%, #E8E8E8 100%);
}

</style>