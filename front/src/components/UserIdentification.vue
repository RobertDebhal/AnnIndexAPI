<template>
<div>

    <div class="row">
        <div class="col-md-6">
            <label for="select_identification">Select identification</label>
            <select id="select_identification" v-model="identification_method" class="form-control">
                <option disabled value="">Please select one</option>
                <option v-for="opt in identification_options">{{opt}}</option>
            </select>
        </div>
        <div class="col-md-6">
            <label for="display_name">Name</label>
            <input id="display_name" v-model="display_name" class="form-control" type="text" disabled>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 mb-3">
            <label for="user_guid">User Guid</label>
            <input id="user_guid" v-model="user_guid" class="form-control" type="text"
                   @keyup.enter="doFindUser"
                   :disabled="identification_method != 'user_guid'">
        </div>
        <div class="col-md-4 mb-3">
            <label for="email">Email</label>
            <input id="email" v-model="email" class="form-control" type="text"
                   @keyup.enter="doFindUser"
                   :disabled="identification_method != 'email'">
        </div>
        <div class="col-md-4 mb-3">
            <label for="user_id">User ID</label>
            <input id="user_id" v-model="user_id" class="form-control" type="text"
                   :disabled="identification_method != 'user_id'">
        </div>

     </div>
</div>

</template>

<script>
import axios from 'axios'
import _ from 'lodash'
export default {
  name: 'user-identification',
  data() {
    return {
        identification_method: '',
        identification_options: ['user_guid', 'email', 'user_id'],
        user_guid: '',
        user_id: '',
        email: '',
        display_name: '',
        error: '',
        if_error: false
    }
  },
  watch: {
    error: function() {
        this.if_error = this.error.length > 0
    },
    user_guid: function() {
        if (this.identification_method == 'user_guid') {
            this.findUser()
        }
    },
    email: function() {
        if (this.identification_method == 'email') {
            this.findUser()
        }
    }
  },
  methods: {
    doFindUser: function() {
        let vm = this
        // resetting user guid to parent components
        vm.$emit('user-guid', '')
        let parameters = {}
        if (this.identification_method == 'user_guid') {
            this.email = ''
            this.user_id = ''
            this.display_name = ''
            parameters.user_uuid = vm.user_guid
        } else if (this.identification_method == 'email') {
            this.user_guid = ''
            this.user_id = ''
            this.display_name = ''
            parameters.email = vm.email
        } else {
            vm.error = 'only email or user guid are currently supported'
            return
        }
        // Backend API Call
        axios.get('/personalization/users', {
            params: parameters
        }).then(function(response) {
            if (response.status == 200) {
                if (vm.identification_method == 'user_guid') {
                    vm.email = response.data.email
                    vm.user_id = response.data.user_id
                } else if (vm.identification_method == 'email') {
                    vm.user_guid = response.data.user_uuid
                    vm.user_id = response.data.user_id
                }
                vm.display_name = response.data.display_name
                vm.error = ''
                vm.$emit('user-guid', vm.user_guid)
                vm.$emit('user-id', vm.user_id)
            }
        }).catch(function (error) {
            vm.error = 'could not find user'
        })
    },
    findUser:
     _.debounce(
        function () {
            this.doFindUser()
        },
        1000
    )
  },
  computed: {
  },
  created: function () {
    let vm = this
    let cookies = document.cookie.split(';')
    let cookiesMap = cookies.map(function(cookie) {
        let keyAndValue = cookie.split('=')
        if (cookies != "") {
            return {
                key: keyAndValue[0].trim(),
                value: keyAndValue[1].trim()
            }
        } else {
            return ""
        }
    })
    let guid = cookiesMap.find(function(element) {
        return element.key == 'guid'
    })
    if (guid != null) {
        this.identification_method = 'user_guid'
        this.user_guid = guid.value.split('_')[1].trim()
    } else {
        this.identification_method = ''
    }
    axios
        // Backend API Call
        .get('/personalization/taxonomies')
        .then(function(response) {
            vm.taxonomyMap = response.data.taxonomies
        })
  }
}
</script>