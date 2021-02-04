<template>
  <v-app>
    <v-navigation-drawer app>
      <Books @selected-book-change="setSelectedBook"/>
    </v-navigation-drawer>
    <v-main>
      <v-container fluid>
        <BookRequests :bookName="selectedBook"/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>

import Books from '@/components/Books'
import BookRequests from '@/components/BookRequests'

export default {
  components: {
    Books,
    BookRequests
  },
  data:()=>({
    selectedBook: null
  }),
  methods:{
    setSelectedBook(val){
      this.selectedBook = val
    }
  }  
}
</script>

