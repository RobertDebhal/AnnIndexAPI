<template>
<div class="container">

    <h1>
        ANN Indexes
    </h1>

    <ul>
        <li v-for="index in indexes"> {{ index }} </li>
    </ul>

    <button v-on:click="getIndexes">Refresh!</button>

</div>
</template>

<script>
import axios from 'axios'
export default {
  name: "ann-indexes",
  data() {
    return {
        indexes: []
    }
  },
  methods: {
    getIndexes: function () {
        let vm = this
        axios
            .get('/ann_indexes')
            .then(function (response) {
                vm.indexes = [response.data]
            })
    }
  },
  created: function () {
    this.getIndexes()
  }
}
</script>

<style>
ul {
    list-style-type: none;
}
</style>