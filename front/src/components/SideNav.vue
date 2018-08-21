<template>
  <div class="sidenav">
    
    <div class="title">
        Nearest Neighbours
    </div>

 </div>
</template>

<script>

export default {
  name: 'SideNav'
}

</script>

<style scoped>

    /* The sidebar menu */
.sidenav {
    height: 100%; /* Full-height: remove this if you want "auto" height */
    width: 240px; /* Set the width of the sidebar */
    position: fixed; /* Fixed Sidebar (stay in place on scroll) */
    z-index: 1; /* Stay on top */
    top: 0; /* Stay at the top */
    left: 0;
    background-color: #000; /* Black */
    overflow-x: hidden; /* Disable horizontal scroll */
    background: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)), url(https://demos.creative-tim.com/vue-light-bootstrap-dashboard/static/img/sidebar-5.jpg);
    background-position: 50% 50%;
    background-size: cover;
    font-family: Roboto, "Helvetica Neue", Arial, sans-serif;
    font-size: 16px;
    font-weight: bold;
    margin: auto;
    text-align: center;
}

.title {
    height: 55px;
    width: 95%;
    margin: 0 2.5% 0 2.5%;
    border-bottom-color: rgba(255, 255, 255, 0.2);
    border-bottom-style: solid;
    border-bottom-width: 1px;
    color: white;
    padding: 10px 0px 10px 0px;
    float: left;
    display: block;
    text-align: center;
    font-size: 20px;
    font-weight: 350;
}
</style>