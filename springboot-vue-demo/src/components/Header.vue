<template>
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
    <div style="width:400px; padding-left: 30px; font-weight:bold;color:SkyBlue;font-size: large">Backstage management system</div>
    <div style="flex: 1"></div>
    <div style="width: 100px; padding-top: 15px">
      <el-dropdown>
    <span  class="el-dropdown-link">
      Tom
      <el-icon>
        <arrow-down />
      </el-icon>
    </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>Personal information</el-dropdown-item>
            <el-dropdown-item>Exit</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { ArrowDown } from '@element-plus/icons-vue'
export default {
  name: "Header",
  components:{
    ArrowDown
  }
}
</script>

<style scoped>
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
</style>