<template>
  <div class="tinymce_container">
    <textarea :id="tinymceId" class="tinymce-textarea"/>
  </div>
</template>

<script>
  import Vue from 'vue'
  import plugins from "./plugin";
  import toolbar from "./toolbar";
  import {getToken} from '@/utils/auth'
  import axios from 'axios'

  Vue.prototype.$axios = axios    //全局注册，使用方法为:this.$axios

  export default {
    name: "Tinymce",
    props: {
      tid: {
        type: String,
        default: function () {
          return 'vue-tinymce-' + +new Date() + ((Math.random() * 1000).toFixed(0) + '')
        }
      },
      value: {
        type: String,
        default: ''
      },
      menubar: {
        // 菜单栏
        type: String,
        default: "file edit insert view format table",
      },
      toolbar: {
        // 工具栏
        type: Array,
        required: false,
        default() {
          return [];
        },
      },
      height: {
        type: String,
        required: false,
        default: "100%",
      },
    },
    data() {
      return {
        editorContent: "",
        uploadImgUrl: process.env.VUE_APP_BASE_API + "/file/upload", // 上传的图片服务器地址
        tinymceId:
          this.tid ||
          "my-tinymce-" + new Date().getTime() + parseInt(Math.random(100)),
        finishInit: false,
        hasChanged: false,
        config: {
          language: "zh_CN",
          convert_urls: false,  //true 上传之后的图片路径会自动转换为本地的相对路径 , false 相反
          height: this.height,
          menubar: this.menubar, //菜单:指定应该出现哪些菜单
          toolbar: this.toolbar.length > 0 ? this.toolbar : toolbar, // 分组工具栏控件
          plugins: plugins, // 插件(比如: advlist | link | image | preview等)
          paste_data_images: true, //是否允许粘贴图像
          object_resizing: false, // 是否禁用表格图片大小调整
          end_container_on_empty_block: true, // enter键 分块
          powerpaste_word_import: "merge", // 是否保留word粘贴样式  clean | merge
          code_dialog_height: 450, // 代码框高度 、宽度
          code_dialog_width: 1000,
          advlist_bullet_styles: "square", // 无序列表 有序列表
        },
      };
    },
    watch: {
      value(val) {
        if ( this.finishInit) {
          this.$nextTick(() =>{
            window.tinymce.get(this.tinymceId).setContent(val)
          })

        }
      },
      editorContent() {
        // console.log(this.editorContent);
        // this.content=this.editorContent;
        // console.log('123');

      }
    },
    methods: {
      uploadFile() {

        formData.append("file", fileObj);
        this.$axios
          .create({
            headers: {
              "Content-Type": "multipart/form-data",
              'Authorization': 'Bearer ' + getToken()
            },
          })
          .post(this.uploadImgUrl, formData)
          .then((result) => {
            const res = result.data;
            if (res.stat == 1) {
            }
          })
          .catch((error) => {
            this.$message.error("上传失败，请稍后重试");
          });
      },

      initTinymce() {
        const _this = this
        window.tinymce.init({
          selector: `#${this.tinymceId}`,
          ...this.config,
          content_style:
            "* { padding:0; margin:0; } img {max-width:100% !important }", // 初始化赋值
          init_instance_callback: (editor) => {
            if (_this.value) {
              editor.setContent(_this.value);
            }
            this.finishInit = true;
            editor.on("NodeChange Change SetContent KeyUp", () => {
              this.hasChanged = true;
            });
          }, // 上传图片
          images_upload_handler: (blobInfo, success, failure) => {
            const formData = new FormData();
            formData.append("file", blobInfo.blob());
            console.log("blobInfo", blobInfo);
            console.log("success", success);
            console.log("failure", failure);
            this.$axios
              .create({
                headers: {
                  "Content-Type": "multipart/form-data",
                  'Authorization': 'Bearer ' + getToken()
                },
              })
              .post(this.uploadImgUrl, formData)
              .then((result) => {
                console.log(result);
                const res = result.data;
                if (res.code == 200) {
                  success(res.data.materialUrl);
                } else {
                  failure(res.msg);
                }
              })
              .catch((error) => {
                failure("上传出错，请稍后重试");
                // this.$message.error("上传失败，请稍后重试");
              });
          },
          setup: (editor) => {
            // 内容发生变化
            /* editor.on("change",(e) => {
              console.log(e);
            }); */
            // 失去焦点
            editor.on("blur", (e) => {
              // console.log(e);
              // console.log(editor.getContent());
              this.editorContent = editor.getContent();
              this.$emit("callbackEB", this.editorContent);
              this.$emit('input', editor.getContent())
            });
            // 输入内容
            /* editor.on("input",(e) => {
              console.log(e);
            }); */
          },
        });
      },
      destroyTinymce() {
        if (window.tinymce.get(this.tinymceId)) {
          window.tinymce.get(this.tinymceId).destroy()
        }
      },
      setContent(value) {
        window.tinymce.get(this.tinymceId).setContent(value)
      },
      getContent() {
        return window.tinymce.get(this.tinymceId).getContent()
      }


    },
    created() {
    },
    mounted() {
      //this.initTinymce();
      this.$nextTick(() => {

        var that = this;
        var t1 = setTimeout(function () {
          that.initTinymce();
          clearTimeout(t1);
        }, 1000)
      });
    },
    beforeDestroy() {
      tinymce.remove(this.tinymceId);
    },
    activated() {
      this.initTinymce()
    },
    deactivated() {
      this.destroyTinymce()
    },
    destroyed() {
      this.destroyTinymce()
    },
  };
</script>

<style lang="scss">
  .tinymce_container {
    width: 100%;
  }
</style>
