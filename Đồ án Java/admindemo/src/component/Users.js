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

export const listUser = (props) => (
  <List {...props}>
    <Datagrid style={{ overflowX: "auto" }}>
      <TextField source="id" />
      <TextField source="name" />
      <TextField source="username" />
      <TextField source="gender" />
      <TextField source="phone" />
      <TextField source="email" />
      <TextField source="photo" />
      <TextField source="role" />
      <TextField source="status" />
      {/* <TextField source="category.title" /> */}
      <EditButton />
    </Datagrid>
  </List>
);

export const editUser = (props) => {
  return (
    <Edit {...props}>
      <SimpleForm>
        <TextInput source="name" />
        <TextInput source="username" />
        <TextInput source="password" />
        <TextInput source="gender" />
        <TextInput source="phone" />
        <TextInput source="email" />
        <TextInput source="photo" />
        <TextInput source="role" />
        <TextInput source="status" />
      </SimpleForm>
    </Edit>
  );
};

export const CreateUser = (props) => {
  const notify = useNotify();
  const redirect = useRedirect();

  const imageUploadFormRef = useRef();

  const onSuccess = (data) => {
    notify(`created successfully`);

    if (imageUploadFormRef.current) {
      imageUploadFormRef.current.handleImageUpload(data.photo);
    }
    redirect("list", "users");
  };

  return (
    <Create {...props} mutationOptions={{ onSuccess }} redirect="users">
      <SimpleForm>
        <TextInput source="name" />
        <TextInput source="username" />
        <TextInput source="password" />
        <TextInput source="gender" />
        <TextInput source="phone" />
        <TextInput source="email" />
        <TextInput source="photo" />
        <TextInput source="role" />
        <TextInput source="status" />
        <ImageUploadForm ref={imageUploadFormRef} />
      </SimpleForm>
    </Create>
  );
};
