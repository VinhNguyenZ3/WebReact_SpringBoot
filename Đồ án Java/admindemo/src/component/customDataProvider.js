import { fetchUtils } from "react-admin";

const apiUrl = "http://localhost:8080/api";
const httpClient = fetchUtils.fetchJson;

const dataProvider = {
  getList: (resource, params) => {
    const { page, perPage } = params.pagination;
    const url = `${apiUrl}/${resource}?page=${page - 1}&size=${perPage}`;

    return httpClient(url)
      .then(({ json }) => {
        return {
          data: json.content, // Extract data from the "content" field
          total: json.totalElements, // Use the "totalElements" field for total count
        };
      })
      .catch((error) => {
        // Handle any errors that occurred during the request
        throw error;
      });
  },
  getOne: (resource, params) => {
    const { id } = params;
    const url = `${apiUrl}/${resource}/${id}`;

    return httpClient(url)
      .then(({ json }) => {
        return {
          data: json, // Return the fetched data
        };
      })
      .catch((error) => {
        // Handle any errors that occurred during the request
        throw error;
      });
  },
  getMany: (resource, params) => {
    const { ids } = params;

    // Construct the URL to fetch multiple records based on their IDs
    const url = `${apiUrl}/${resource}?ids=${ids.join(",")}`;

    return httpClient(url)
      .then(({ json }) => {
        return {
          data: json.content, // Return the fetched data
        };
      })
      .catch((error) => {
        // Handle any errors that occurred during the request
        throw error;
      });
  },
  update: (resource, params) => {
    const { id, data } = params;
    const url = `${apiUrl}/${resource}/${id}`;
    const options = {
      method: "PUT",
      body: JSON.stringify(data),
    };

    return httpClient(url, options)
      .then(({ json }) => {
        return {
          data: json, // Return the updated data
        };
      })
      .catch((error) => {
        // Handle any errors that occurred during the request
        throw error;
      });
  },
  delete: (resource, params) => {
    const { id } = params;
    const url = `${apiUrl}/${resource}/${id}`;
    const options = {
      method: "DELETE",
    };

    return httpClient(url, options)
      .then(({ json }) => {
        return {
          data: json, // Return any response data, if needed
        };
      })
      .catch((error) => {
        // Handle any errors that occurred during the request
        throw error;
      });
  },
  create: (resource, params) => {
    const { data } = params;
    const url = `${apiUrl}/${resource}`;
    const options = {
      method: "POST",
      body: JSON.stringify(data),
    };

    return httpClient(url, options)
      .then(({ json }) => {
        return {
          data: { ...data, id: json.id }, // Assuming the API returns the ID of the created resource
        };
      })
      .catch((error) => {
        // Handle any errors that occurred during the request
        throw error;
      });
  },
};

export default dataProvider;
