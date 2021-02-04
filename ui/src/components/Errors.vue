<template>
<v-container>
    <chapter-selector :bookName="bookName" v-model="chapter" />
    <v-btn 
      @click="search"
      :disabled="bookName === null"
    >
      Найти
    </v-btn>

<v-simple-table dense>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            Символ
          </th>
          <th class="text-left">
            Количество
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="item in errors"
          :key="item.character"
        >
          <td>{{ item.character }}</td>
          <td>{{ item.count }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
    
</v-container>
</template>

<script>

import axios from 'axios'
import ChapterSelector from '@/components/ChapterSelector'

export default {
  name: 'ChapterWordCount',
  props:{
    bookName: String
  },
  components: {
    ChapterSelector
  },
  data:()=>({
    chapter: 1,
    word: "",
    errors: [],

  }),

  methods:{
    search(){
      axios.get(`/api/error/${this.bookName}/${this.chapter}`)
      .then(response => {
        this.errors = response.data;
      })
    }
  }
}
</script>
