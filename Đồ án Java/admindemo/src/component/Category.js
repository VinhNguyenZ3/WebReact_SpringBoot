import React, { useRef } from "react";
import {
  List,
  Datagrid,
  TextField,
  Edit,
  SimpleForm,
  EditButton,
  TextInput,
  Create,
  useNotify,
  useRedirect,
} from "react-admin";
import ImageUploadForm from "./ImageUploadForm";

// ... Các components khác

export const listCategory = (props) => (
  <List {...props}>
    <Datagrid>
      <TextField source="id" />
      <TextField source="title" />
      <TextField source="description" />
      <TextField source="slug" />
      <TextField source="photo" />
      <TextField source="parent_id" />
      <TextField source="sort_order" />
      <TextField source="status" />
      <EditButton />
    </Datagrid>
  </List>
);

export const editCategory = (props) => (
  <Edit {...props}>
    <SimpleForm>
      <TextInput source="title" />
      <TextInput source="description" />
      <TextInput source="slug" />
      <TextInput source="photo" />
      <TextInput source="parent_id" />
      <TextInput source="sort_order" />
      <TextInput source="status" />
    </SimpleForm>
  </Edit>
);

// export const createCategory = (props) => {
//   const notify = useNotify();
//   const redirect = useRedirect();

//   const imageUploadFormRef = useRef();

//   const onSuccess = (data) => {
//     notify(`created successfully`);

//     if (imageUploadFormRef.current) {
//       imageUploadFormRef.current.handleImageUpload(data.photo);
//     }
//     redirect("list", "users");
//   };
//   return (
//     <Create {...props} mutationOptions={{ onSuccess }} redirect="categories">
//       <SimpleForm>
//         <TextInput source="title" />
//         <TextInput source="description" />
//         <TextInput source="slug" />
//         <TextInput source="parent_id" />
//         <TextInput source="sort_order" />
//         <TextInput source="status" />
//         <TextInput source="photo" />
//       </SimpleForm>
//     </Create>
//   );
// };
export const createCategory = (props) => {
  // const notify = useNotify();
  // const redirect = useRedirect();

  // const imageUploadFormRef = useRef();

  // const onSuccess = (data) => {
  //   notify(`created successfully`);

  //   if (imageUploadFormRef.current) {
  //     imageUploadFormRef.current.handleImageUpload(data.photo);
  //   }
  //   redirect("list", "users");
  // };
  return (
    <Create {...props}
    //  mutationOptions={{ onSuccess }} redirect="categories"
     >
      <SimpleForm>
        <TextInput source="title" />
        <TextInput source="description" />
        <TextInput source="slug" />
        <TextInput source="parent_id" />
        <TextInput source="sort_order" />
        <TextInput source="status" />
        <TextInput source="photo" />
      </SimpleForm>
    </Create>
  );
};
