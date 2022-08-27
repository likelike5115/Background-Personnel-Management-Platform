<template>
  <div>
    <el-row class="tac">
      <el-col :span="24">
        <el-menu
            style="width:200px; min-height: calc(100vh - 50px)"
            default-active="2"
            class="el-menu-vertical-demo"
        >
          <el-sub-menu index="1-4">
            <template #title>
              <el-icon><location /></el-icon>
              <span>Navigator One</span>
            </template>
              <el-menu-item index="1-4-1">&emsp;item one</el-menu-item>

          </el-sub-menu>
          <el-menu-item index="2">
            <el-icon><icon-menu /></el-icon>
            <span>Navigator Two</span>
          </el-menu-item>
          <el-menu-item index="3" disabled>
            <el-icon><document /></el-icon>
            <span>Navigator Three</span>
          </el-menu-item>
          <el-menu-item index="4">
            <el-icon><setting /></el-icon>
            <span>Navigator Four</span>
          </el-menu-item>
        </el-menu>
      </el-col>

    </el-row>
  </div>
</template>

<script>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'

export default {
  name: "Aside",
  components:{
    Document,
    IconMenu,
    Location,
    Setting
  }
}
</script>

<style scoped>

</style>