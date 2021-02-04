<template>
  <div class="home">
    <v-dialog
      v-model="dialog"
      width="500"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="red lighten-2"
          dark
          v-bind="attrs"
          v-on="on"
        >
          Загрузить
        </v-btn>
      </template>

      <v-card>
        <v-card-title class="headline grey lighten-2">
          Загрузка книги
        </v-card-title>
        <v-container>
          <v-text-field label="Имя" v-model="uploadBookName"></v-text-field>
          <v-file-input label = "Файл" show-size v-model="uploadBookFile"></v-file-input>
        </v-container>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            @click="uploadBook()"
          >
           Загрузить
          </v-btn>
          <v-btn
            color="secondary"
            text
            @click="dialog = false"
          >
           Отмена
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog
      v-model="loading"
      persistent
      width="300"
    >
      <v-card
        color="primary"
        dark
      >
      <v-container>
        <v-progress-circular
            indeterminate
            color="white"
            class="mb-0"
        ></v-progress-circular>
      </v-container>
      </v-card>
    </v-dialog>

    <v-list
        dense
        nav
      >
      <v-list-item-group v-model="selected" v-on:change="$emit('selected-book-change', books[selected].name)">
        <v-list-item
          v-for="book in books"
          :key="book.name"
          link
        >
          <v-list-item-content>
            <v-list-item-title>{{book.name}}</v-list-item-title>
          </v-list-item-content>
          <v-list-item-action v-if="!book.splited">
            <v-btn @click="splitBook(book.name)" icon>
               <v-icon color="grey lighten-1">mdi-reload</v-icon>
            </v-btn>
         </v-list-item-action>
        </v-list-item>
      </v-list-item-group>
      </v-list>
  </div>
</template>

<script>
// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'

import axios from 'axios'

export default {
  name: 'Books',
  components: {
  },
  data:()=>({
    books:[],
    dialog: false,
    uploadBookName: "",
    uploadBookFile: null,
    loading: false,
    selected: -1
  }),

  mounted(){
    this.update()
  },

  methods:{
    update(){
      axios.get('/api/book')
        .then(response => {
          this.books = response.data
          this.selected = -1
        })
    },

    uploadBook(){
      this.dialog = false
      this.loading = true
      var data = new FormData();
      data.append('name', this.uploadBookName)
      data.append('file', this.uploadBookFile)

      axios.put('/api/book',data)
      .then(()=> {
          this.update();
          this.loading = false;
        })
      .catch(()=> {
          this.update();
          this.loading = false;
        })
    },

    splitBook(bookName){
      this.loading = true
      axios.post(`/api/book/${bookName}/split`)
        .then(()=> {
            this.update();
            this.loading = false;
          })
        .catch(()=> {
            this.update();
            this.loading = false;
        })
    }
  }
}
</script>
