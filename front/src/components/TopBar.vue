 <template>
<div class="main">
 <div class="top">
      
        <ul>
          <router-link :to="{ name: 'AnnoyIndexes' }">Annoy Indexes</router-link>
          <router-link :to="{ name: 'AnnoyIndexesExplorer' }">Annoy Indexes Explorer</router-link>
        </ul>
  
</div>
</div>

</template>

<script>

export default {
  name: 'NavBar'
}

</script>

<style scoped>

.top ul {
  list-style-type: none;
  padding: 0 0 0 0.75%;
  margin: 0;
  overflow: hidden;
  background: linear-gradient(to top left, rgb(10, 12, 10) 0%, rgb(120, 124, 120) 100%);
  height: 55px;
}

.top a {
  float: left;
  display: block;
  color: white;
  text-align: center;
  padding: 10px 10px;
  height: 55px;
  min-width: 40px;
}

.top a:hover {
  background: linear-gradient(to bottom, #F5F5F5 0%, #E8E8E8 100%);
  cursor: pointer;
  text-decoration: none;
  color: black;
}

.top {
  height: 55px;
  width: 100%;
  font-family: Roboto, "Helvetica Neue", Arial, sans-serif;
  font-size: 20px;
  font-weight: 300;
}

.main {
    margin-left: 240px;
}

</style>