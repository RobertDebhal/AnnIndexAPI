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
  border: 1px solid #e7e7e7;
  background: linear-gradient(to bottom left, #FFFFFF 0%, rgb(250,250,250) 100%);
  height: 55px;
}

.top a {
  float: left;
  display: block;
  color: rgb(136,136,136);
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