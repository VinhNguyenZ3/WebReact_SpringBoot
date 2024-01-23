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

export const listBrand = (props) => (
  <List {...props}>
    <Datagrid>
      <TextField source="id" />
      <TextField source="name" />
      <TextField source="slug" />
      <TextField source="description" />
      <TextField source="photo" />

      <EditButton />
    </Datagrid>
  </List>
);

export const editBrand = (props) => (
  <Edit {...props}>
    <SimpleForm>
      <TextInput source="name" />
      <TextField source="slug" />
      <TextInput source="description" multiline fullWidth />
      <TextField source="photo" />
    </SimpleForm>
  </Edit>
);

export const CreateBrand = (props) => {
  const redirect = useRedirect();
  const notify = useNotify();

  const imageUploadFormRef = useRef();

  const onSuccess = (data) => {
    notify(`created successfully`);

    if (imageUploadFormRef.current) {
      imageUploadFormRef.current.handleImageUpload(data.photo);
    }
    redirect("list", "products");
  };
  return (
    <Create {...props} mutationOptions={{ onSuccess }} redirect="brands" >
      <SimpleForm>
        <TextInput source="name" />
        <TextInput source="slug" />
        <TextInput source="description" multiline fullWidth />
        <TextInput source="photo" />
        <ImageUploadForm ref={imageUploadFormRef} />
      </SimpleForm>
    </Create>
  );
};
